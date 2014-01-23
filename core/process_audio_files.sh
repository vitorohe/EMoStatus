#!/bin/bash

# Script to execute emostatus.sh per each audio file that need to be processed

USER="root"
PASS=""
HOST="localhost"
DBNAME="emostatusdb"


if [ $PASS ]
	then
	recordings=($(mysql -u $USER -h$HOST -p$PASS $DBNAME -e "SELECT id,path FROM recordings where status_process='WAITING'"))
else
	recordings=($(mysql -u $USER -h$HOST $DBNAME -e "SELECT id,path FROM recordings where status_process='WAITING'"))
fi

cnt=${#recordings[@]}

for (( i=2 ; i<cnt ; i++ ))
do
	id=${recordings[$i]}
	i=$(( i+1 ))
	path=${recordings[$i]}
	echo "Processing file $path with id $id"
	./emostatus.sh -a $path -i $id
done