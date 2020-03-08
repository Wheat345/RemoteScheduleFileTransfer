#!/bin/bash 
sourceHost=$1
sourcePath=$2
sshpass -p "password" ssh dif@$sourceHost rm $sourcePath*

