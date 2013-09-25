#!/bin/bash

AUDIO_DIR=$1
CUT=$2
MODEL=$3
SVM_PROB=$4


# per audio file generate audio chunks
audio_files=`ls $AUDIO_DIR*.wav`

for audio_file in $audio_files; do
	audio_name=`basename $audio_file`
	audio_name=${audio_name:0:${#audio_name}-4}
	
	# params
	AUDIO=$audio_file
	OUTPUT_DIR=$AUDIO_DIR$audio_name

	./test_model_audio.sh $AUDIO $CUT $MODEL $OUTPUT_DIR $SVM_PROB
done
