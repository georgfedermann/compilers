#!/usr/bin/env bash
rm *.java || true
javacc v01AstParser.jj && dos2unix *.java
# the following line will be taken care of by the IDE
# javac *.java
