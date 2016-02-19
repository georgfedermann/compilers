rm *.java
rm *.class
javacc lang.jj && dos2unix *.java
javac *.java
java MyParser < HelloWorld.mj

