rm *.java
rm *.class
javacc lang.jj
javac *.java
java MyParser < HelloWorld.mj

