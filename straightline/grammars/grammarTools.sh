#!/usr/bin/env bash

ARGS=
while getopts ":vhc:" opt; do
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

# echo $ARGS

java -cp slt-jar-with-dependencies.jar org.poormanscastle.studies.compilers.utils.grammartools.GrammarTools $ARGS

