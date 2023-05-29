package com.space.hp.language.compiler;


import com.google.auto.service.AutoService;
import com.space.hp.language.annotation.AutoLanguage;
import com.space.hp.language.compiler.utils.ClassTypeHelper;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * @author HePing
 * @date 2023/5/23
 * @description
 */
@AutoService(Processor.class)
public class LanguageRouteProcessor extends AbstractProcessor {

    private Filer filer;
    private Messager messager;
    private Elements elementUtils;
    private Types typeUtils;
    private ClassTypeHelper classTypeHelper;

    private final Map<String, List<VariableElement>> elementMap = new HashMap<>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
        elementUtils = processingEnv.getElementUtils();
        typeUtils = processingEnv.getTypeUtils();
        classTypeHelper = new ClassTypeHelper(elementUtils, typeUtils);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(AutoLanguage.class.getCanonicalName());
        return set;
    }

    private boolean checkAnnotationIsValid(Element element) {
        if (element.getKind() != ElementKind.FIELD) {
            messager.printMessage(Diagnostic.Kind.NOTE, "%s must be declared on field.", element);
            return false;
        }
        if (element.getModifiers().contains(Modifier.PRIVATE)) {
            messager.printMessage(Diagnostic.Kind.NOTE, "%s can not be private.", element);
            return false;
        }
        if (!classTypeHelper.isView(element.asType())) {
            messager.printMessage(Diagnostic.Kind.NOTE, "%s must be View.", element);
            return false;
        }
        return true;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        messager.printMessage(Diagnostic.Kind.NOTE, "annotation size--->" + annotations.size());

        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(AutoLanguage.class);
        if (elements == null || elements.isEmpty()) {
            return true;
        }

        for (Element element : elements) {
            if (checkAnnotationIsValid(element)) {
                VariableElement variableElement = (VariableElement) element;
                TypeElement enclosingElement = (TypeElement) variableElement.getEnclosingElement();
                String className = enclosingElement.getQualifiedName().toString();

                List<VariableElement> elementList = elementMap.computeIfAbsent(className, k -> new ArrayList<>());
                elementList.add(variableElement);
            }
        }

        if (elementMap.isEmpty()) {
            return true;
        }

        for (String key : elementMap.keySet()) {
            buildJavaFile(key, elementMap.get(key));
        }
        return true;
    }


    private void buildJavaFile(String className, List<VariableElement> elements) {
        try {
            TypeElement enclosingElement = (TypeElement) elements.get(0).getEnclosingElement();

            MethodSpec.Builder constructorBuilder = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(TypeName.get(enclosingElement.asType()), "target")
                .addStatement("this.target = target")
                .addStatement("$T.INSTANCE.registerObserver(target, this)", classTypeHelper.getClassTypeName(Constants.LANGUAGE_MANAGER_CLASS_NAME));

            MethodSpec.Builder onLanguageChangedBuilder = MethodSpec.methodBuilder("onLanguageChanged")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(elementUtils.getTypeElement(Constants.CONTEXT_CLASS_NAME).asType()), "context");

            for (VariableElement element : elements) {
                messager.printMessage(Diagnostic.Kind.NOTE, "buildJavaFile element " + element.getSimpleName() + " " + element.getAnnotation(AutoLanguage.class).value());

                if (classTypeHelper.isEditText(element.asType())) {
                    onLanguageChangedBuilder.addStatement(String.format(
                        "target.%s.setHint($T.getString(context, \"%s\"))",
                        element.getSimpleName(),
                        element.getAnnotation(AutoLanguage.class).value()), classTypeHelper.getClassTypeName(Constants.LANGUAGE_UTIL_CLASS_NAME));
                } else if (classTypeHelper.isTextView(element.asType())) {
                    onLanguageChangedBuilder.addStatement(String.format(
                        "target.%s.setText($T.getString(context, \"%s\"))",
                        element.getSimpleName(),
                        element.getAnnotation(AutoLanguage.class).value()), classTypeHelper.getClassTypeName(Constants.LANGUAGE_UTIL_CLASS_NAME));
                }
            }

            TypeSpec.Builder typeBuilder = TypeSpec.classBuilder(getSimpleClassName(className) + Constants.DECORATOR_SUF)
                .addModifiers(Modifier.PUBLIC)
                .addField(TypeName.get(enclosingElement.asType()), "target")
                .addSuperinterface(classTypeHelper.getClassTypeName(Constants.LANGUAGE_OBSERVER_CLASS_NAME))
                .addMethod(constructorBuilder.build())
                .addMethod(onLanguageChangedBuilder.build());

            JavaFile javaFile = JavaFile.builder(getPackage(className), typeBuilder.build())
                .build();
            javaFile.writeTo(filer);
        } catch (Exception e) {
            messager.printMessage(Diagnostic.Kind.NOTE, "buildJavaFile fail" + e);
        }
    }

    private String getPackage(String qualifier) {
        return qualifier.substring(0, qualifier.lastIndexOf("."));
    }

    private String getSimpleClassName(String qualifier) {
        return qualifier.substring(qualifier.lastIndexOf(".") + 1);
    }
}
