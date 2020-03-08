#!/bin/bash 
sourceHost=$1
sourcePath=$2
sshpass -p "password" ssh user@$sourceHost rm $sourcePath*

