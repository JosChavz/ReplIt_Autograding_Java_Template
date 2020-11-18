#! /bin/bash
javac Main.java
VARIABLE=`java Main`

if cat Main.java | grep -i "ReadyForSubmission=Y"; 
then
  javac -classpath .:/run_dir/junit-4.12.jar:target/dependency/* -d . JUnitTest.java Main.java
  java -classpath .:/run_dir/junit-4.12.jar:/run_dir/hamcrest-core-1.3.jar:target/dependency/* JUnitTest "$VARIABLE"
else
  javac Main.java 
  java Main
fi