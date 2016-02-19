#!/usr/bin/env bash
rm *.java
javacc Interpreter.jj && dos2unix *.java
# the next command will be taken care of by the IDE.
# javac *.java

