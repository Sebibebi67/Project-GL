#!/bin/bash

	bool1=0
	bool2=0
    echo "Compilation in progress"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml tools/Main.java && bool1=1
    if [[ bool1 == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    if [[ bool2 == 0 ]];then
        echo "Update failed"
        exit 1
    fi
    echo "Update controlors"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml sample/*.java && bool2=1

    echo "Start of application"
	java --module-path ./lib --add-modules javafx.controls,javafx.fxml tools.Main &