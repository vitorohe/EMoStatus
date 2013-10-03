#!/bin/bash

AUDIO_FILE=$1

file=`basename $AUDIO_FILE`
tmp_file="/tmp/$file"
output=$(sox $AUDIO_FILE -n stats 2>&1)
dc_offset=`echo "$output" | grep -e 'DC offset' | awk '{print $3}'`
echo $dc_offset
dc_offset=`echo 0.0-$dc_offset | bc`
sox $AUDIO_FILE $tmp_file dcshift $dc_offset
rm $AUDIO_FILE
cp $tmp_file $AUDIO_FILE
rm $tmp_file
