#!/bin/bash

AUDIO_FILE=$1

file=`basename $AUDIO_FILE`
tmp_file="/tmp/$file"
sox --norm=-10 $AUDIO_FILE $tmp_file
rm $AUDIO_FILE
cp $tmp_file $AUDIO_FILE
rm $tmp_file
