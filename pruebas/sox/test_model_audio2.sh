#!/bin/bash

# guardar variable nombre archivo audio
AUDIO=$1
CUT=$2
MODEL=$3
OUTPUT_DIR=$4
SVM_PROB=$5

# rec audio in file 15 sec
# rec $AUDIO trim 0 15

# create folder
# mkdir $OUTPUT_DIR

# cp $AUDIO $OUTPUT_DIR

# call openSmile and extract prosodyShs features
SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"
SMILExtract -C $SMILE_DIR/config/prosodyShs.conf -I $AUDIO -O $OUTPUT_DIR/prosody_$OUTPUT_DIR.csv


# extract feautres from all audio or from every chunk
if [ "$CUT" -eq "1" ]:
	then
	
	# call python script and use sox to cut audio in words or chunks
	python read_prosody_csv.py --csv $OUTPUT_DIR/prosody_$OUTPUT_DIR.csv --au $AUDIO --outdir $OUTPUT_DIR

	files=`ls $OUTPUT_DIR/*.wav`
	for file in $files; do
		SMILExtract -C $SMILE_DIR/config/emobase2010.conf -I $file -O $OUTPUT_DIR/$OUTPUT_DIR.arff
	done
	
else
	SMILExtract -C $SMILE_DIR/config/emobase2010.conf -I $AUDIO -O $OUTPUT_DIR/$OUTPUT_DIR.arff
fi

# delete string field in arff file
sed "s/'noname',//g" -i $OUTPUT_DIR/$OUTPUT_DIR.arff
sed "s/@attribute name string//g" -i $OUTPUT_DIR/$OUTPUT_DIR.arff

# transform arff to libsvm
perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $OUTPUT_DIR/$OUTPUT_DIR.arff

# predict svm
$SMILE_DIR/scripts/modeltrain/libsvm-small/svm-predict -b $SVM_PROB $OUTPUT_DIR/$OUTPUT_DIR.lsvm $MODEL $OUTPUT_DIR/$OUTPUT_DIR-predict