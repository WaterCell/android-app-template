Android App Template
====

Androidアプリのテンプレートです。

## 入っているもの

* Google Play Services
* appcompat-v7
* [JsonPullParser](https://github.com/vvakame/JsonPullParser)
* [Crashlytics](https://www.crashlytics.com) コメントアウトしてあります（後述）
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

以下の場所からプラグインをインストールしてください

* Preferences > Plugins > Browse Repositories... > Android Studio Unit Test

`android.sourceSets.setRoot("src/test")` などでテストフォルダを弄ってあると動きません。

## Crashlytics

https://get.fabric.io/crashlytics

正規のAPI KEYをセットしてないとエラーになるので、 https://github.com/WaterCell/android-app-template/commit/793078a457d1d56457bd0b02a296436225fb6da8 でコメントアウトしました。
使うときは手動でコメントを外すか、 `git revert 793078a` で戻してください。

## Special Thanks

[zaki50/android_gradle_template](https://github.com/zaki50/android_gradle_template)

## License

```
Copyright 2015 WaterCell Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
