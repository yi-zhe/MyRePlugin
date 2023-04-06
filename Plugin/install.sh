./gradlew clean 
./gradlew :app:assembleDebug
rm ../RePluginHost/app/src/main/assets/plugins/demo1.jar
cp ./app/build/outputs/apk/debug/app-debug.apk ../RePluginHost/app/src/main/assets/plugins/demo1.jar

