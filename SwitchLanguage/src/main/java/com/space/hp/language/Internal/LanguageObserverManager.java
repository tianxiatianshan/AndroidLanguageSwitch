package com.space.hp.language.Internal;

import android.content.Context;
import com.space.hp.language.LanguageObserver;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: HePing
 * @date: 2019/12/26
 * @description:
 */
public class LanguageObserverManager {

    /**
     * 使用虚引用保存Activity或Fragment引用，防止内存泄漏
     */
    private List<WeakReference<LanguageObserver>> mObserverWeakRefs = new LinkedList<>();

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

    public void removeObserver(LanguageObserver languageObserver) {
        Iterator<WeakReference<LanguageObserver>> iterator = mObserverWeakRefs.iterator();
        while(iterator.hasNext()) {
            WeakReference<LanguageObserver> weakReference = iterator.next();
            if (weakReference.get() == languageObserver) {
                iterator.remove();
            }
        }
    }

    public void noticeLanguageObserver(Context context) {
        Iterator<WeakReference<LanguageObserver>> iterator = mObserverWeakRefs.iterator();
        while(iterator.hasNext()) {
            WeakReference<LanguageObserver> weakReference = iterator.next();
            if (weakReference.get() != null) {
                weakReference.get().onLanguageChanged(context);
            } else {
                iterator.remove();
            }
        }
    }
}
