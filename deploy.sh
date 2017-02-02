#!/usr/bin/env bash

# Add configs
# local.properties
# - bintray.user=your_bintray_user_name
# - bintray.apikey=your_bintray_api_key
#
# bintray url: https://bintray.com/khacpv/maven/Calendar_Day_View

./gradlew install
./gradlew bintrayUpload

echo 'Upload to JCenter successfully!'
echo 'Visit: https://bintray.com/khacpv/maven/Calendar_Day_View to update the newest version!'