#!/bin/bash -e
./gradlew clean
./gradlew annotations:dokka -i | grep -i 'No documentation for com' && { echo 'kdoc validation failed'; exit 1; }
./gradlew base:dokka -i | grep -i 'No documentation for com' && { echo 'kdoc validation failed'; exit 1; }
exit 0