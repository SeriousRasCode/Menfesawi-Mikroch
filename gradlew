#!/bin/bash
GRADLE_VERSION=6.7.1
wget -q "https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip" -O gradle.zip
unzip -q gradle.zip
mv "gradle-${GRADLE_VERSION}" gradle
export GRADLE_HOME=$PWD/gradle
export PATH=$GRADLE_HOME/bin:$PATH
./gradle/bin/gradle "$@"
