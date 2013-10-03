#!/bin/bash

# params
NAME=$1
SVM_FILES_DIR=$2
SVM_TYPE=$3
SVM_KERNEL=$4
SVM_DEGREE=$5
SVM_GAMMA=$6
SVM_C=$7
SVM_PROB=$8
CONF_NAME=$9

SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"

# look for lsvm files in dir and join them in one
svm_files_names=`find $SVM_FILES_DIR*/ -name \*$CONF_NAME.lsvm | sort`

touch $NAME

num_class=1

for svm_file in $svm_files_names; do
	# change number of class per file and join to the main one
	file=`basename $svm_file`
	tmp_file=`mktemp /tmp/$file.XXXXXX`
	cat $svm_file > $tmp_file
	sed "s/^0 1/$num_class.0 1/g" -i $tmp_file
	sed "s/0\.0/$num_class.0/g" -i $tmp_file
	cat $tmp_file >> $NAME
	rm $tmp_file
	num_class=$(( num_class+1 ))
done

# train model with params
$SMILE_DIR/scripts/modeltrain/libsvm-small/svm-train -s $SVM_TYPE -t $SVM_KERNEL -d $SVM_DEGREE -c $SVM_C -b $SVM_PROB $NAME $NAME.model