#!/bin/bash

# Basic Params

SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"
DATA_DIR="../data"

# Init program
# Here we should have some options, help, arguments, etc.
# There should be a function that always print help options

if [ "$#" -eq "0" ]
	then
	echo ""
	echo "No commandline options were given."
 	echo "Please run ' emostatus -h ' to see some usage information!"
 	echo ""

	exit 0
fi

if [[ $1 != "-"* ]]
	then
	echo "Invalid option: $1"
	exit 1
fi

while getopts ":a:h" opt; do
  	case $opt in
    	a)
      		AUDIO_FILE=$OPTARG
      		echo $AUDIO_FILE
      		;;
    	h)
			echo "Help Information"
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


# Copy file to data directory
cp $AUDIO_FILE $DATA_DIR/

audio_base_wav=`basename $AUDIO_FILE`
audio_base=${audio_base_wav%.*}

AUDIO_FILE=$DATA_DIR/$audio_base_wav

# Convert audio file to 16 KHz and mono
./convert_audio_file.sh $AUDIO_FILE 16

# Extract chunks

OUTPUT_DIR=$DATA_DIR/$audio_base

SMILExtract -C $SMILE_DIR/config/prosodyShs.conf -I $AUDIO_FILE -O $DATA_DIR/prosody_$audio_base.csv
python read_prosody_csv.py --au $AUDIO_FILE --csv $DATA_DIR/prosody_$audio_base.csv --outdir $OUTPUT_DIR --outname $audio_base

# Save chunk files in db

# For every chunk detect emotion first group filter


# For every chunk detect emotion second group filter


# For every chunk detect gender according to emotion detected


# 
