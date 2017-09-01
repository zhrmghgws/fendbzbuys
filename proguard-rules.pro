# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/lichao/macsdk/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-optimizationpasses 5          # 指定代码的压缩级别
-dontusemixedcaseclassnames   # 是否使用大小写混合
-dontpreverify           # 混淆时是否做预校验
-verbose                # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*  # 混淆时所采用的算法


-keep public class * extends android.app.Activity      # 保持哪些类不被混淆
-keep public class * extends android.app.Application   # 保持哪些类不被混淆
-keep public class * extends android.app.Service       # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference        # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService    # 保持哪些类不被混淆

-keepclasseswithmembernames class * {  # 保持 native 方法不被混淆
    native <methods>;
}
-keepclasseswithmembers class * {   # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {# 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclassmembers class * extends android.app.Activity { # 保持自定义控件类不被混淆
    public void *(android.view.View);
}
-keep public class * extends application.android.com.xindai.moduler.uploadcontacts_moduler.BaseHolder
-keep class * implements java.io.Serializable
-keep class com.bigkoo.pickerview.**{*;}
-keep class domain.**{*;}
-keepclassmembers class * implements java.io.Serializable {
   *;
}
-keepclassmembers enum * {     # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable { # 保持 Parcelable 不被混淆
    public static final android.os.Parcelable$Creator *;
}
#==================gson==========================
-dontwarn com.google.**
-keep class com.google.gson.** { *; }
#==================protobuf======================
#-------------------------------retrofit2-------------------------------
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-keep public class retrofit2.** {*;}
-keep class retrofit2.adapter.rxjava.**{*;}
-keep class retrofit2.adapter.rxjava.CompletableHelper$Inner{

    public <fields>;

   public <methods>;

}
-dontwarn okio.**
-dontwarn retrofit2.**


#-------------------------------retrofit2-------------------------------

#-------------------------------RxJava-------------------------------
#http://www.on1024.com/2016/01/13/%E5%8D%87%E7%BA%A7%E5%88%B0%20RxAndroid%201.x.x%20%E4%B9%8B%E5%90%8E%E6%B7%B7%E6%B7%86%E8%A6%81%E6%B3%A8%E6%84%8F%E7%9A%84/
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keep public class rx.** {*;}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#-------------------------------RxJava-------------------------------

#-------------------------------okhttp3-------------------------------
-keepattributes Signature
 -keepattributes *Annotation*
 -keep class okhttp3.** {*; }
 -keep interface okhttp3.** {*; }
 -dontwarn okhttp3.**
#-------------------------------okhttp3-------------------------------
-keepnames class com.example.xh.glidedemo.ConfigurationGlide
#youmeng tongji----------------------
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class com.hxd.fendbzbuys.R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#---------------------------

#youmeng tuisong----------------------
-dontwarn com.taobao.**
-dontwarn anet.channel.**
-dontwarn anetwork.channel.**
-dontwarn org.android.**
-dontwarn org.apache.thrift.**
-dontwarn com.xiaomi.**
-dontwarn com.huawei.**

-keepattributes *Annotation*

-keep class com.taobao.** {*;}
-keep class org.android.** {*;}
-keep class anet.channel.** {*;}
-keep class com.umeng.** {*;}
-keep class com.xiaomi.** {*;}
-keep class com.huawei.** {*;}
-keep class org.apache.thrift.** {*;}

-keep class com.alibaba.sdk.android.**{*;}
-keep class com.ut.**{*;}
-keep class com.ta.**{*;}

-keep public class **.R$*{
   public static final int *;
}

#（可选）避免Log打印输出
-assumenosideeffects class android.util.Log {
   public static *** v(...);
   public static *** d(...);
   public static *** i(...);
   public static *** w(...);
 }
#==============================================
-keepclassmembers class * {
    public <init> (org.json.JSONObject);
}
-keep public class com.hxd.fendbzbuys.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-dontoptimize
-dontpreverify


-keep class com.hxd.fendbzbuys.domain.**{*;}
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
# If you do not use SQLCipher:
-dontwarn org.greenrobot.greendao.database.**
# If you do not use Rx:
-dontwarn rx.**