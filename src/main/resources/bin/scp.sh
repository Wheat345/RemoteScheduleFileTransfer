#!/bin/bash 
sourceHost=$1
sourcePath=$2
destinationHost=$3 #TODO: use this varible in remote destination.
destinationPath=$4
mkdir $destinationPath
sshpass -p "password" scp -r dif@$sourceHost:$sourcePath $destinationPath
