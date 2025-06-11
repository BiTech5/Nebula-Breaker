mkdir -p bin

find src -name "*.java" -type f | xargs javac -d bin

cd bin
java src.main.Main