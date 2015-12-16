rm *.java
rm *.class
javacc.sh lang.jj
javac *.java
java MyParser < HelloWorld.mj

