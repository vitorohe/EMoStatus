#!/bin/bash

AUDIO_FILE=$1
LEVEL=$2
file=`basename $AUDIO_FILE`
tmp_file="/tmp/$file"
sox --norm=$LEVEL $AUDIO_FILE $tmp_file
rm $AUDIO_FILE
cp $tmp_file $AUDIO_FILE
rm $tmp_file
