# ActivityStarter

## I'am 9527

## ActivityStart 是一Activity启动器，封装activity启动过程。旨在不改变系统原来Intent功能前提下，减少版本判断，在减少学习使用成本

* 支持 onActivityResult 优雅回调
* 支持高版本API activityOptions 启动方式
* 防止快速点击打开多个Activity
* 启动不存在Activity再也不担心闪退

## 用法
```java
ActivityStarter.with(this)
    .setIntent(intent)
    .setActivityOptions(bundle)
    .startActivity(new OnResultListener() {
        @Override
        public void onActivityResult(int resultCode, Intent data) {
            // 回调....
        }
    });
```
具体用法请转到 [demo](https://github.com/tangsh/ActivityStarter/blob/master/demo/src/main/java/com/tsh/activitystarter/demo/MainActivity.java) 项目

## Android Studio 配置

* app build.gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

* 项目 build.gradle

```
compile 'com.github.tangsh:ActivityStarter:v1.1.0'
```


## 欢迎在wiki 提交宝贵意见