#!/bin/bash

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files from the root directory
javac -d bin src/main/Main.java src/util/GameFrame.java src/ui/HomePage.java

# Run the game from the bin directory
cd bin
java src.main.Main 