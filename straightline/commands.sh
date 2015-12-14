#!/usr/bin/env bash

pushd target
java -cp slt-jar-with-dependencies.jar org.poormanscastle.studies.compilers.programs.benchmark.FrequencyCounter 1 < /Users/georg/college/Compiler/tinyTale.txt
java -cp slt-jar-with-dependencies.jar org.poormanscastle.studies.compilers.programs.benchmark.FrequencyCounter 8 < /Users/georg/college/Compiler/tale.txt
popd

