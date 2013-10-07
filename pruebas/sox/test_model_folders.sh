#!/bin/bash

AUDIO_DIR=$1
MODEL=$2
SVM_PROB=$3
SUB_CAT=$4

model_base=`basename $MODEL`
model_base=${model_base%.*.*}

SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"

folders=`ls -d $AUDIO_DIR*/`

conf_name="IS10_paraling"
num_class=1
for folder in $folders; do
	folder_name=`basename $folder`
	lsvm_files=`find $folder -name \*$conf_name.lsvm`
	if [ "$lsvm_files" == "" ]
		then
		audio_files=`ls $folder/*.wav`
		for audio_file in $audio_files; do
			# ./convert_audio_file.sh $audio_file
			SMILExtract -C $SMILE_DIR/config/$conf_name.conf -I $audio_file -O $folder/$folder_name-$conf_name.arff
		done

		# delete string field in arff file
		
		sed "s/'noname',//g" -i $folder/$folder_name-$conf_name.arff
		sed "s/@attribute name string//g" -i $folder/$folder_name-$conf_name.arff		

		# transform arff to libsvm
		perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $folder/$folder_name-$conf_name.arff
	fi
	# predict svm
	$SMILE_DIR/scripts/modeltrain/libsvm-small/svm-predict -b $SVM_PROB $folder/$folder_name-$conf_name.lsvm $MODEL $folder/$folder_name-$model_base-predict
	
	# if [ "$SUB_CAT" == "1" ]
	# 	then
	# 	python read_predict.py --pf $folder$folder_name-$model_base-predict --cn $num_class

	# 	mkdir $folder$folder_name-wavs

	# 	filename="$folder/$folder_name-$model_base-predict-wavs"
	# 	while read -r line
	# 	do
	# 	    name=$line
	# 	    cp $name $folder$folder_name-wavs
	# 	done < "$filename"
	# else
	python read_predict.py --pf $folder/$folder_name-$model_base-predict
	# fi
	num_class=$(( num_class+1 ))
done
