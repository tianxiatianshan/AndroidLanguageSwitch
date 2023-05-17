# AndroidLanguageSwitch
安卓动态切换语言库.....  无缝切换

特点：
* 不改变多语言的存放方式，和系统一致 在res下
* 不设置语言时，自动跟随系统语言
* 设置语言后，立即动态切换为设置语言

# 使用方法
 1. 参照demo BaseActivity 继承LanguageObserver，并重写onCreate, attachBaseContext, onConfigurationChanged, onDestroy

```
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.INSTANCE.registerObserver(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageManager.INSTANCE.getLanguageContext(newBase));
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LanguageManager.INSTANCE.onConfigurationChanged(this, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LanguageManager.INSTANCE.unRegisterObserver(this);
    }
 ```

2.  实现onLanguageChanged， 更新页面语言显示 （在设置语言后，会立即回调该接口）
```
    @Override
    public void onLanguageChanged(Context context) {
        mTextView.setText(context.getResources().getString(R.string.hello));
    }
```
# 接口
//注册语言变化监听
```
LanguageManager.INSTANCE.registerObserver(LanguageObserver observer);
```
//取消注册语言变化监听
```
LanguageManager.INSTANCE.unRegisterObserver(LanguageObserver observer);
```
//设置语言: 语言码为：An ISO 639 alpha-2 or alpha-3 language code, or a language subtag up to 8 characters in length. See the Locale class description about valid language values.
```    
LanguageManager.INSTANCE.setLanguage(Context context, String language);
```
//自定义语言码和Locale转换关系（高级）
```
LanguageManager.INSTANCE.setLanguageConverter(LanguageStrLocaleConverter languageConverter)
```
//获取当前多语言
```
LanguageManager.INSTANCE.getCurrentLanguage(Context context);
```
//设置自动多语言（跟随系统多语言）
```
LanguageManager.INSTANCE.setAutoLanguage(Context context);
```









