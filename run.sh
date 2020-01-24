#!/bin/bash

# This command shell has been made by Sébastien HERT, member of the PDB Team, group 8
#
# It is supposed to allow everyone to compile the GL project on a Linux Distribution without problems

	mainJava=tools/Main.java
	main=tools.Main
	controllersPackage=controllers

	bool1=0
	bool2=0
    echo "Compilation in progress"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml $mainJava && bool1=1
    if [[ $bool1 == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    echo "Update controllers"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml $controllersPackage/*.java && bool2=1
        if [[ $bool2 == 0 ]];then
        echo "Update failed"
        exit 1
    fi

    echo "Start of application"
	java --module-path ./lib --add-modules javafx.controls,javafx.fxml $main &