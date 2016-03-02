#!/usr/bin/env bash

#TOOLS_TARGET_PATH=/d/02eex612/Projects/ProjectHit/sources/compilers/straightline/grammars/
TOOLS_TARGET_PATH=/Users/georg/sources/projects/compilers/straightline/grammars/
TOOLS_JAR_NAME=slt-jar-with-dependencies.jar

TARGET_FOLDER=target/

print_help() {
    echo "build-tool v0.1"
    echo "Usage: ./build.sh arg"
    echo "Default action is to execute maven builds and copy the artefacts to specified locations on the file system."
    echo
    echo "t  build grammar tools package and deploy it to $TOOLS_TARGET_PATH"
    echo "h  print this help"
}

case $1 in
  t)
    echo "Building grammar tools package"
    mvn clean package assembly:single && cp ${TARGET_FOLDER}${TOOLS_JAR_NAME} ${TOOLS_TARGET_PATH}
    ;;
  h)
    print_help
    ;;
  *)
    print_help
    ;;
esac 


