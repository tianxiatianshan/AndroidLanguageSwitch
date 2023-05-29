package com.space.hp.language.Internal;

import android.content.Context;

import com.space.hp.language.LanguageManager;
import com.space.hp.language.LanguageObserver;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.WeakHashMap;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class LanguageObserverManager {

    /**
     * 使用虚引用保存Activity或Fragment引用，防止内存泄漏
     */
    private List<WeakReference<?>> mObserverWeakRefs = new LinkedList<>();
    private HashMap<WeakReference<?>, LanguageObserver> map = new HashMap<>();

    private LanguageObserverManager(){
    }

    private static class Holder {
        private static final LanguageObserverManager INSTANCE = new LanguageObserverManager();
    }

    public static LanguageObserverManager getInstance() {
        return Holder.INSTANCE;
    }

    public void addObserver(LanguageObserver languageObserver) {
        mObserverWeakRefs.add(new WeakReference<>(languageObserver));
    }

    public void addObserver(Object object, LanguageObserver languageObserver) {
        WeakReference<Object> objectWeakReference = new WeakReference<>(object);
        mObserverWeakRefs.add(objectWeakReference);
        map.put(objectWeakReference, languageObserver);
    }

    public void removeObserver(LanguageObserver languageObserver) {
        Iterator<WeakReference<?>> iterator = mObserverWeakRefs.iterator();
        while(iterator.hasNext()) {
            WeakReference<?> weakReference = iterator.next();
            if (weakReference.get() == languageObserver) {
                iterator.remove();
            }
        }
    }

    public void noticeLanguageObserver(Context context) {
        Iterator<WeakReference<?>> iterator = mObserverWeakRefs.iterator();
        while(iterator.hasNext()) {
            WeakReference<?> weakReference = iterator.next();
            Object object = weakReference.get();
            if (object == null){
                iterator.remove();
                map.remove(weakReference);
            } else if (object instanceof LanguageObserver) {
                ((LanguageObserver)object).onLanguageChanged(context);
            } else {
                LanguageObserver languageObserver = map.get(weakReference);
                if (languageObserver != null) {
                    languageObserver.onLanguageChanged(context);
                }
            }
        }
    }
}
