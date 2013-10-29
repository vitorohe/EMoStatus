#!/bin/bash

AUDIO_FILE=$1

file=`basename $AUDIO_FILE`
tmp_file="/tmp/$file"
sox $AUDIO_FILE $tmp_file lowpass 1k
rm $AUDIO_FILE
cp $tmp_file $AUDIO_FILE
rm $tmp_file
