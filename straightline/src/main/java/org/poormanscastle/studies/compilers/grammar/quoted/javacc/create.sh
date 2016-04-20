#!/usr/bin/env bash
rm *.java || true
javacc Quoted.jj && dos2unix *.java
# the following line will be taken care of by the IDE
# javac *.java
