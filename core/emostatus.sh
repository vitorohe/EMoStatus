#!/bin/bash

# Init program
# Here we should have some options, help, arguments, etc.

if [ "$#" -eq "0" ]
	then
	echo "Help options"
	exit 0
fi

while getopts ":a:" opt; do
  case $opt in
    a)
      AUDIO_FILE=$OPTARG
      echo $AUDIO_FILE
      ;;
    \?)
      echo "Invalid option: -$OPTARG" >&2
      exit 1
      ;;
    :)
      echo "Option -$OPTARG requires an argument." >&2
      exit 1
      ;;
  esac
done

# Input audio file
# AUDIO_FILE=$1

# Convert audio file to 16 KHz and mono
# ./convert_audio_file.sh $AUDIO_FILE 16

# Extract chunks
# SMILExtract -C $OPENSMILE_DIR/config/prosodyShs.conf -I $AUDIO_FILE -O prosody_$base_audio_file.csv
# python read_prosody.py --

# For every chunk detect emotion first group filter


# For every chunk detect emotion second group filter


# For every chunk detect gender according to emotion detected


# 
