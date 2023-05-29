# AndroidLanguageSwitch
安卓动态切换语言库.....  无需应用重启即可生效

特点：
* 快速：无需应用重启即可生效，设置语言后，可实现立即动态切换为设置语言
* 不改变多语言的存放方式，和系统一致在res下
* 不设置语言时，自动跟随系统语言

# 效果预览

 ![image](https://github.com/tianxiatianshan/AndroidLanguageSwitch/blob/master/%E6%95%88%E6%9E%9C%E9%A2%84%E8%A7%88.gif)

# 使用方法
 
 [![](https://jitpack.io/v/tianxiatianshan/AndroidLanguageSwitch.svg)](https://jitpack.io/#tianxiatianshan/AndroidLanguageSwitch)
```
    allprojects {
        repositories {
        	...
            maven { url 'https://jitpack.io' }
        }
    }
    
    dependencies {
        implementation 'com.github.tianxiatianshan.AndroidLanguageSwitch:SwitchLanguage:1.0.1'
        implementation 'com.github.tianxiatianshan.AndroidLanguageSwitch:SwitchLanguageAnnotation:1.0.1'
        annotationProcessor 'com.github.tianxiatianshan.AndroidLanguageSwitch:SwitchLanguageCompiler:1.0.1'
    }
```

 1. 参照demo BaseActivity 继承LanguageObserver，并重写onCreate, attachBaseContext, onConfigurationChanged, onDestroy

```
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LanguageManager.INSTANCE.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LanguageManager.INSTANCE.getLanguageContext(newBase));
    }

 ```
 
 2. 增加注解实现语言动态切换AutoLanguage, value为多语言定义名（避免动态ID）
 ```
    @AutoLanguage("enter_account")
    EditText userEd;
    

    @AutoLanguage("login")
    Button loginBt;
 ```


# 接口

### 1. 设置语言: 
语言码为：An ISO 639 alpha-2 or alpha-3 language code, or a language subtag up to 8 characters in length. See the Locale class description about valid language values.
```    
LanguageManager.INSTANCE.setLanguage(Context context, String language);
```

### 2. 自定义语言码和Locale转换关系（高级）
```
LanguageManager.INSTANCE.setLanguageConverter(LanguageStrLocaleConverter languageConverter)
```

### 3. 获取当前多语言
```
LanguageManager.INSTANCE.getCurrentLanguage(Context context);
```

### 4. 设置自动多语言（跟随系统多语言）
```
LanguageManager.INSTANCE.setAutoLanguage(Context context);
```






