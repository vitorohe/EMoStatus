#!/bin/bash

AUDIO_DIR=$1
CONF_NAME=$2

# openSmile path
SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"

# per audio file generate audio chunks
folders=`ls -d $AUDIO_DIR*/`

conf_name=$CONF_NAME
#conf_name="avec2013_functionals"
lsvm_files=`find $AUDIO_DIR*/ -name \*$conf_name.lsvm`

# echo $svm_files
# exit 0

if [ "$lsvm_files" == "" ]
	then
	for folder in $folders; do
		folder_name=`basename $folder`
		
		audio_files=`ls $folder*.wav`
		
		for audio_file in $audio_files; do
			# ./convert_audio_file.sh $audio_file
			# ./normalize_audio_file.sh $audio_file
			# ./lowpass_audio_file.sh $audio_file
			SMILExtract -C $SMILE_DIR/config/$conf_name.conf -I $audio_file -O $folder/$folder_name-$conf_name.arff
			#SMILExtract -C $SMILE_DIR/scripts/avec2013/$conf_name.conf -I $audio_file -O $folder/$folder_name-$conf_name.arff
			# output=$(SMILExtract -C $SMILE_DIR/config/emobase2010.conf -I $audio_file -O $folder/$folder_name-emobase2010.arff 2>&1)
		done
		
		sed "s/'.*',//g" -i $folder/$folder_name-$conf_name.arff
		sed "s/@attribute name string//g" -i $folder/$folder_name-$conf_name.arff
		
		# transform arff to libsvm
		perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $folder/$folder_name-$conf_name.arff
	done
fi

# call create_model script
# params
basename_dir=`basename $AUDIO_DIR`
NAME=$AUDIO_DIR$basename_dir-$conf_name-poly3gc.lsvm
SVM_FILES_DIR=$AUDIO_DIR
SVM_TYPE=0 # C-SVC=0, nu-SVC=1, one-class=3  
SVM_KERNEL=1 # Linear=0, Polynomial=1, RBF=2
SVM_DEGREE=3
SVM_GAMMA=1
SVM_C=1
SVM_PROB=1

# ./create_model.sh $NAME $SVM_FILES_DIR $SVM_TYPE $SVM_KERNEL $SVM_DEGREE $SVM_GAMMA $SVM_PROB
./create_model.sh $NAME $SVM_FILES_DIR $SVM_TYPE $SVM_KERNEL $SVM_DEGREE $SVM_GAMMA $SVM_C $SVM_PROB $conf_name
