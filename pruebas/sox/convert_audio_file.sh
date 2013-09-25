#!/bin/bash

AUDIO_FILE=$1

file=`basename $AUDIO_FILE`
tmp_file="/tmp/$file"
sox $AUDIO_FILE -c 1 -b 16 -e signed-integer -r 16k $tmp_file
rm $AUDIO_FILE
cp $tmp_file $AUDIO_FILE
rm $tmp_file
