#!/bin/bash

AUDIO_DIR=$1
OUTPUT_DIR=$2

SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"

audio_files=`find $AUDIO_DIR -name \*.wav`

for audio_file in $audio_files; do
	audio_base=`basename $audio_file`
	audio_base=${audio_base%.*}
	SMILExtract -C $SMILE_DIR/config/prosodyShs.conf -I $audio_file -O prosody_$audio_base.csv
	python read_prosody_csv.py --au $audio_file --csv prosody_$audio_base.csv --outdir $OUTPUT_DIR --ch --sout
done
