#!/bin/bash

# This command shell has been made by Sébastien HERT, member of the PDB Team, group 8
#
# It is supposed to allow everyone to compile the GL project on a Linux Distribution without problems

	mainJava=tools/*.java
	main=tools.Main
	controllersPackage=controllers

	bool=0
    echo "Compilation in progress"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml $mainJava && bool=1
    if [[ $bool == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    javac --module-path ./lib --add-modules javafx.controls,javafx.fxml user/*.java && bool=1
    if [[ $bool == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    javac --module-path ./lib --add-modules javafx.controls,javafx.fxml admin/*.java && bool=1
    if [[ $bool == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    javac --module-path ./lib --add-modules javafx.controls,javafx.fxml study/*.java && bool=1
    if [[ $bool == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    echo "Update controllers"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml $controllersPackage/*.java && bool=1
        if [[ $bool == 0 ]];then
        echo "Update failed"
        exit 1
    fi

    echo "Start of application"
	java --module-path ./lib --add-modules javafx.controls,javafx.fxml $main &