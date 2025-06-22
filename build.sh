mkdir -p bin

javac -d bin $(find src -name "*.java")


cd bin
java src.main.Main
