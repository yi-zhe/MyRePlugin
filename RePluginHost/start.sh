./gradlew clean
adb uninstall com.qihoo360.replugin.sample.host
./gradlew :app:assembleDebug
adb install ./app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.qihoo360.replugin.sample.host/.MainActivity 
