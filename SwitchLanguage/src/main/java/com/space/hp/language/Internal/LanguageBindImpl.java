package com.space.hp.language.Internal;

import com.space.hp.language.api.ILanguageBind;

import java.lang.reflect.Constructor;

/**
 * @author HePing
 * @date 2023/5/26
 * @description
 */
public class LanguageBindImpl implements ILanguageBind {

    private static final String DECORATOR_SUF = "_languageDecorator";

    @Override
    public void bind(Object object) {
        try {
            Class<?> aClass = Class.forName(object.getClass().getName() + DECORATOR_SUF);
            Constructor<?> constructor = aClass.getConstructor(object.getClass());
            constructor.newInstance(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void unBind(Object object) {
    }
}
