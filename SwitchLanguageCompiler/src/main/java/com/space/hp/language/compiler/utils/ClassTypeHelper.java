package com.space.hp.language.compiler.utils;

import com.space.hp.language.compiler.Constants;
import com.squareup.javapoet.TypeName;

import java.util.List;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * @author HePing
 * @date 2023/5/29
 * @description
 */
public class ClassTypeHelper {

    private Elements elementUtils;
    private Types typeUtils;

    public ClassTypeHelper(Elements elements, Types types) {
        this.elementUtils = elements;
        this.typeUtils = types;
    }

    public TypeName getClassTypeName(String className) {
        return TypeName.get(elementUtils.getTypeElement(className).asType());
    }

    public boolean isView(TypeMirror typeMirror) {
        return recognitionType(typeMirror, Constants.VIEW_CLASS_NAME);
    }

    public boolean isEditText(TypeMirror typeMirror) {
        return recognitionType(typeMirror, Constants.EDIT_TEXT_CLASS_NAME);
    }

    public boolean isTextView(TypeMirror typeMirror) {
        return recognitionType(typeMirror, Constants.TEXT_VIEW_CLASS_NAME);
    }

    /**
     * 循环判断
     * @param type
     * @param className
     * @return
     */
    public boolean recognitionType(TypeMirror type, String className) {
        if (type.toString().equals(className)) {
            return true;
        }
        List<? extends TypeMirror> supers = typeUtils.directSupertypes(type);
        if (supers.size() == 0) {
            return false;
        }
        for (TypeMirror superType : supers) {
            if (superType.toString().equals(className) || recognitionType(superType, className)) {
                return true;
            }
        }
        return false;
    }
}
