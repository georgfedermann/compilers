#!/usr/bin/env bash

#JAR_HOME=/d/02eex612/Projects/ProjectHit/sources/compilers/straightline/grammars/
JAR_HOME=/Users/georg/sources/projects/compilers/straightline/grammars/
JAR_NAME=slt-jar-with-dependencies.jar

ARGS=
while getopts "aehvwc:" opt; do
  case $opt in
    v)
      # echo "-v was triggered!" >&2
      ARGS="v $ARGS"
      ;;
    h)
      # echo "-h was triggered!" >&2
      ARGS="h $ARGS"
      ;;
    c)
      # echo "-c was triggered!" >&2
      ARGS="c $OPTARG $ARGS"
      ;;
    a)
      ARGS="a $ARGS"
      ;;
    e)
      ARGS="e $ARGS"
      ;;
    w)
      ARGS="w $ARGS"
      ;;
    :)
      # echo "Option -$OPTARG requires an argument." >&2
      ARGS="c invalid"
      ;;
    \?)
      # echo "Invalid Option: -$OPTARG" >&2
      exit 1
      ;;
  esac
done

#echo $ARGS
java -cp "${JAR_HOME}${JAR_NAME}" org.poormanscastle.studies.compilers.utils.grammartools.GrammarTools $ARGS

