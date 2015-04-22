# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/Android Studio.app/sdk/tools/proguard/proguard-android.txt
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

-repackageclasses

# for Google Play Services (see http://developer.android.com/google/play-services/setup.html )
-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# for Crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**
# (see http://support.crashlytics.com/knowledgebase/articles/202143-eclipse-with-proguard)
-keepattributes SourceFile, LineNumberTable

# for OkHttp (see https://github.com/square/okio/issues/60#issuecomment-45401424)
-dontwarn okio.**

# for Picasso (see https://github.com/square/picasso#proguard)
-dontwarn com.squareup.okhttp.**

# Joda-Time
-dontwarn org.joda.time.**

# for Butter Knife (see http://jakewharton.github.io/butterknife/#proguard)
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# for (see https://github.com/greenrobot/EventBus#proguard-configuration)
-keepclassmembers class ** {
    public void onEvent*(**);
}
