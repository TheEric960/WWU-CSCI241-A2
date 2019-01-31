
all: test

build: AVL.java Vocab.java
	javac AVL.java Vocab.java

buildtest: AVLTest.java TestRunner.java build
	javac -cp .:/Users/scott/csci241/repo/assignments/a3/solution/lib/junit-4.12.jar AVLTest.java TestRunner.java

test: buildtest
	#java -cp .:/Users/scott/csci241/repo/assignments/a3/solution/lib/junit-4.12.jar:/Users/scott/csci241/repo/assignments/a3/solution/lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore AVLTest
	java -cp .:/Users/scott/csci241/repo/assignments/a3/solution/lib/junit-4.12.jar:/Users/scott/csci241/repo/assignments/a3/solution/lib/hamcrest-core-1.3.jar TestRunner

