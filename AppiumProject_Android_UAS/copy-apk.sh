#!/usr/bin/env bash
set -e

SOURCE_APK="${1:-../AndroidDemoApp/Android_Appium_Demo.apk}"
TARGET_DIR="apps"
TARGET_APK="$TARGET_DIR/Android_Appium_Demo.apk"

mkdir -p "$TARGET_DIR"

if [ ! -f "$SOURCE_APK" ]; then
  echo "APK tidak ditemukan di: $SOURCE_APK"
  echo "Gunakan: ./copy-apk.sh /path/ke/Android_Appium_Demo.apk"
  exit 1
fi

cp "$SOURCE_APK" "$TARGET_APK"
echo "APK berhasil dicopy ke: $TARGET_APK"
