#!/bin/bash

AUDIO_DIR=$1
MODEL=$2
SVM_PROB=$3

SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"


folder=$AUDIO_DIR
folder_name=`basename $folder`
conf_name="emobase2010"
lsvm_files=`find $folder -name \*$conf_name.lsvm`
if [ "$lsvm_files" == "" ]
	then
	audio_files=`ls $folder*.wav`
	for audio_file in $audio_files; do
		# ./convert_audio_file.sh $audio_file
		SMILExtract -C $SMILE_DIR/config/$conf_name.conf -I $audio_file -O $folder$folder_name-$conf_name.arff
	done

	# delete string field in arff file
	
	sed "s/'noname',//g" -i $folder$folder_name-$conf_name.arff
	sed "s/@attribute name string//g" -i $folder$folder_name-$conf_name.arff		

	# transform arff to libsvm
	perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $folder$folder_name-$conf_name.arff
fi
# predict svm
$SMILE_DIR/scripts/modeltrain/libsvm-small/svm-predict -b $SVM_PROB $folder$folder_name-$conf_name.lsvm $MODEL $folder$folder_name-$model_base-predict
python read_predict.py --clpf $folder$folder_name-$model_base-predict

