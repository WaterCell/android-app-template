Android App Template
====

Androidアプリのテンプレートです。

## 入っているもの
* Google Play Services
* appcompat-v7
* [JsonPullParser](https://github.com/vvakame/JsonPullParser)
* [Crashlytics](https://www.crashlytics.com)
* [OkHttp](http://square.github.io/okhttp/)
* [Picasso](http://square.github.io/picasso/)
* [Joda-Time](http://www.joda.org/joda-time/)
* [PhotoView](https://github.com/chrisbanes/PhotoView)
* [Butter Knife](http://jakewharton.github.io/butterknife/)
* [EventBus](https://github.com/greenrobot/EventBus)
* JUnit
* Robolectric
* Espresso

## テスト
### Robolectricテスト
`app/src/test/java/package_name/`以下に配置

以下のように実行

```
$ ./gradlew test[build_variant]
```

### Espressoテスト
`app/src/androidTest/java/package_name/`以下に配置

以下のように実行 (端末またはエミュレータを接続しておく)

```
$ ./gradlew connectedAndroidTest
```

### Lintチェック
以下のように実行

```
$ ./gradlew lint[build_variant]
```

## Android Studio連携

以下の場所から導入します。

* Preferences > Plugins > Browse Repositories... > Android Studio Unit Test

`android.sourceSets.setRoot("src/test")` などでテストフォルダを弄ってあると動きません。