#!/bin/bash

# Basic Params

SMILE_DIR="/home/vito/Descargas/Programas/opensmile-2.0-rc1/opensmile"
DATA_DIR="../data"
MODEL_DIR="../audio_models"

USER="root"
PASS=""
HOST="localhost"
DBNAME="emostatusdb"

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

while getopts ":a:h:i:" opt; do
  	case $opt in
    	a)
      		AUDIO_FILE=$OPTARG
      		echo $AUDIO_FILE
      		;;
      	i)
			ID_AUDIO_FILE=$OPTARG
			echo $ID_AUDIO_FILE
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

# File should be in the determined user directory
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
python read_prosody_csv.py --au $AUDIO_FILE --csv $DATA_DIR/prosody_$audio_base.csv --outdir $OUTPUT_DIR --ch

# Save chunk files in db

chunk_files=`find $OUTPUT_DIR -name \*.wav | sort`

conf_name_first_classification="emobase2010"
conf_name_second_classification="emobase2010"

# echo $chunk_files #(listo)
for file in $chunk_files; do
	if [ $PASS ]
		then
		mysql -u $USER -h$HOST -p$PASS $DBNAME -e "INSERT INTO chunks (name,recording_id) VALUES('$file',$ID_AUDIO_FILE)"
	else
		mysql -u $USER -h$HOST $DBNAME -e "INSERT INTO chunks (name,recording_id) VALUES('$file',$ID_AUDIO_FILE)"
	fi
	echo "$file in database"
	SMILExtract -C $SMILE_DIR/config/$conf_name_first_classification.conf -I $file -O $OUTPUT_DIR/$audio_base-1-$conf_name_first_classification.arff
	SMILExtract -C $SMILE_DIR/config/$conf_name_first_classification.conf -I $file -O $OUTPUT_DIR/$audio_base-2-$conf_name_second_classification.arff
done

# delete string field in arff files
sed "s/'noname',//g" -i $OUTPUT_DIR/$audio_base-1-$conf_name_first_classification.arff
sed "s/@attribute name string//g" -i $OUTPUT_DIR/$audio_base-1-$conf_name_first_classification.arff

sed "s/'noname',//g" -i $OUTPUT_DIR/$audio_base-2-$conf_name_second_classification.arff
sed "s/@attribute name string//g" -i $OUTPUT_DIR/$audio_base-2-$conf_name_second_classification.arff

# transform arff to libsvm
perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $OUTPUT_DIR/$audio_base-1-$conf_name_first_classification.arff
perl $SMILE_DIR/scripts/modeltrain/arffToLsvm.pl $OUTPUT_DIR/$audio_base-2-$conf_name_second_classification.arff


model_first_classification="$MODEL_DIR/class-three-groups-emobase2010-linear.lsvm.model"
model_second_classification="$MODEL_DIR/class-AV-emobase2010-linear.lsvm.model"

model_first_classif_base=`basename $model_first_classification`
model_first_classif_base=${model_first_classif_base%.*.*}

model_second_classif_base=`basename $model_second_classification`
model_second_classif_base=${model_second_classif_base%.*.*}

# For every chunk detect emotion first group filter
$SMILE_DIR/scripts/modeltrain/libsvm-small/svm-predict -b 1 $OUTPUT_DIR/$audio_base-1-$conf_name_first_classification.lsvm $model_first_classification $OUTPUT_DIR/$audio_base-$model_first_classif_base-predict

# Save classification to db
echo "Save first classification to db"
python read_predict.py --clpfdb $OUTPUT_DIR/$audio_base-$model_first_classif_base-predict --rid $ID_AUDIO_FILE --classifn 1

# For every chunk detect emotion second group filter
$SMILE_DIR/scripts/modeltrain/libsvm-small/svm-predict -b 1 $OUTPUT_DIR/$audio_base-2-$conf_name_second_classification.lsvm $model_second_classification $OUTPUT_DIR/$audio_base-$model_second_classif_base-predict

# Save classification to db
echo "Save second classification to db"
python read_predict.py --clpfdb $OUTPUT_DIR/$audio_base-$model_second_classif_base-predict --rid $ID_AUDIO_FILE --classifn 2




# For every chunk detect gender according to emotion detected


# 
