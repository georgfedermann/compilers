#!/usr/bin/env bash
rm *.java
javacc parser.jj && dos2unix *.java
# the following line will be taken care of by the IDE
# javac *.java
