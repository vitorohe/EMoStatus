#!/bin/bash

AUDIO_FILE=$1
RATE=$2

file=`basename $AUDIO_FILE`
tmp_file="/tmp/$file"
sox $AUDIO_FILE -c 1 -b 16 -e signed-integer -r "$RATE"k $tmp_file
rm $AUDIO_FILE
cp $tmp_file $AUDIO_FILE
rm $tmp_file
