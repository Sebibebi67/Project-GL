#!/bin/bash

# This command shell has been made by Sébastien HERT, member of the PDB Team, group 8
#
# It is supposed to allow everyone to compile the GL project on a Linux Distribution without problems

	mainJava=*/*.java
	main=tools.Main

	bool=0
    echo "Compilation in progress"
	javac --module-path ./lib --add-modules javafx.controls,javafx.fxml $mainJava && bool=1
    if [[ $bool == 0 ]];then
        echo "Compilation failed"
        exit 1
    fi

    echo "Start of application"
	java --module-path ./lib --add-modules javafx.controls,javafx.fxml $main &