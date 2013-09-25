#!/bin/bash

AUDIO_DIR=$1

# openSmile path
SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"

# per audio file generate audio chunks
audio_files=`ls $AUDIO_DIR*.wav`

for audio_file in $audio_files; do
	audio_name=`basename $audio_file`
	audio_name=${audio_name:0:${#audio_name}-4}

	# create folder per audio file
	mkdir $AUDIO_DIR$audio_name

	# extract prosodic feauters to found pauses
	SMILExtract -C $SMILE_DIR/config/prosodyShs.conf -I $audio_file -O $AUDIO_DIR$audio_name/prosody_$audio_name.csv

	# cut audio file in chunks
	# python read_prosody_csv.py --csv $AUDIO_DIR$audio_name/prosody_$audio_name.csv --au $audio_file --outdir $AUDIO_DIR$audio_name
	cp $audio_file $AUDIO_DIR$audio_name
	# get n random chunks to extract features and create model
	random_files=`ls $AUDIO_DIR$audio_name/*.wav | shuf -n5`

	touch $AUDIO_DIR$audio_name/selected_files

	for random_file in $random_files; do
		echo $random_file >> $AUDIO_DIR$audio_name/selected_files
		SMILExtract -C $SMILE_DIR/config/emobase2010.conf -I $random_file -O $AUDIO_DIR$audio_name/$audio_name.arff
	done

	# delete string field in arff file
	sed "s/'noname',//g" -i $AUDIO_DIR$audio_name/$audio_name.arff
	sed "s/@attribute name string//g" -i $AUDIO_DIR$audio_name/$audio_name.arff

	# transform arff to libsvm
	perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $AUDIO_DIR$audio_name/$audio_name.arff
done

# call create_model script
# params
basename_dir=`basename $AUDIO_DIR`
NAME=$AUDIO_DIR$basename_dir.lsvm
SVM_FILES_DIR=$AUDIO_DIR
SVM_TYPE=0 # C-SVC
SVM_KERNEL=1 # Polynomial
SVM_DEGREE=2
SVM_GAMMA=2
SVM_PROB=1

./create_model.sh $NAME $SVM_FILES_DIR $SVM_TYPE $SVM_KERNEL $SVM_DEGREE $SVM_GAMMA $SVM_PROB
