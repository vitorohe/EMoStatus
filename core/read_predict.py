#read_prosody_csv.py
import csv
import mysql.connector
from shutil import copy
from os import system, listdir, path, makedirs
from numpy import mean, divide, round

database_user="root"
database_password=""
database_host="localhost"
database="emostatusdb"



def get_dir_wav_files(predict_path):
	predict_dir = path.dirname(predict_path)
	dir_files = listdir(predict_dir)
	wav_files = []

	for wfile in dir_files:
		if wfile.endswith('.wav'):
			wav_files.append(predict_dir + '/' + wfile)

	wav_files = sorted(wav_files)
	return wav_files


def classify_pf_wav_filename(pf_filename):

	wav_files = get_dir_wav_files(pf_filename)
	i = 0
	
	with open(pf_filename, 'rb') as pffile:
		data = csv.reader(pffile, delimiter=' ')
		
		labels = data.next()
		labels = labels[1:]

		predict_dir = path.dirname(pf_filename)
		
		for label in labels:
			if not path.exists(predict_dir+'/'+label):
				makedirs(predict_dir+'/'+label)
		
		for row in data:
			copy(wav_files[i],predict_dir+'/'+row[0])
			i = i + 1

def classify_pf_wav_filename_to_db(pf_filename, recording_id, first_classif=True, second_classif=False):
	
	wav_files = get_dir_wav_files(pf_filename)
	i = 0
	
	with open(pf_filename, 'rb') as pffile:
		data = csv.reader(pffile, delimiter=' ')
		
		labels = data.next()

		predict_dir = path.dirname(pf_filename)

		cnx = None
		cursor = None

		cnx = mysql.connector.connect(user=database_user, password=database_password,
			host=database_host,
			database=database)

		cursor = cnx.cursor()

		for row in data:
			if first_classif:
				query_update = "UPDATE chunks SET first_classification_group_id={0} WHERE name='{1}' AND recording_id={2}".format(row[0],wav_files[i],recording_id)
			elif second_classif:
				query_update = "UPDATE chunks SET second_classification_group_id={0} WHERE name='{1}' AND recording_id={2}".format(row[0],wav_files[i],recording_id)				

			cursor.execute(query_update)

			i = i + 1

		if cnx != None:
			cnx.commit()
			cnx.close()

		if cursor != None:
			cursor.close()


def add_pf_wav_filename(pf_filename):

	rows = []	
	wav_files = get_dir_wav_files(pf_filename)
	i = 0
	
	with open(pf_filename, 'rb') as pffile:
		data = csv.reader(pffile, delimiter=' ')
		
		data.next()
		for row in data:
			rows.append(', '.join(row) + ', ' + path.basename(wav_files[i]))
			i = i + 1


	filename = pf_filename + "wavs_filename"
	
	with open(filename, "a") as file_results:
	
		for row in rows:
			
			file_results.write("%s\n" % row) 
		
		file_results.close()

def process_pf(pf_filename,class_number = None):

	prediction = {}

	if not class_number is None:
		right_classified_files = []
		wav_files = get_dir_wav_files(pf_filename)

	i = 0

	with open(pf_filename, 'rb') as pffile:
		data = csv.reader(pffile, delimiter=' ')
		
		titles = data.next()
		for title in titles:
			prediction[title] = []
		
		for row in data:
			items = zip(titles, row)
			
			for (name, value) in items:
				prediction[name].append(float(value.strip()))
				if not class_number is None and name == 'labels' and class_number == value.strip():
					right_classified_files.append(wav_files[i])

			i = i + 1
		pffile.close()

	filename = pf_filename + "-results"
	keys = prediction.keys()
	keys = sorted(keys)
	
	with open(filename, "a") as file_results:
	
		for key in keys:
			mean_k = mean(prediction[key])
			file_results.write("%s %s %s\n" % (key, mean_k, float("{0}".format(round(mean_k,3)))*100)) 
		
		file_results.close()

	if not class_number is None:
		wavs_filename = pf_filename + "-wavs"
		with open(wavs_filename, "a") as file_wavs:
	
			for wfile in right_classified_files:
				file_wavs.write("%s\n" % wfile)
		
		file_wavs.close()		
	
if __name__ == '__main__':

	#Command Arguments parser and help generator
	from optparse import OptionParser

	_cmd_parser = OptionParser(usage="usage: %prog [options]")
	_opt = _cmd_parser.add_option
	_opt("--pf", help="Set predict filename to process")
	_opt("--cn", help="Set correct predict class number")
	_opt("--pfwav", help="Set predict filename to add wavs filename")
	_opt("--clpf", help="Set predict filename classify audio files")
	_opt("--clpfdb", help="Save classification result to db")
	_opt("--rid", help="Recording id")
	_opt("--classifn", help="Number of classification")

	_cmd_options, _cmd_args = _cmd_parser.parse_args()

	opt, args, parser = _cmd_options, _cmd_args, _cmd_parser

	pf_filename = None
	class_number = None

	if not opt.pf is None:
		pf_filename = opt.pf
		class_number = opt.cn

		process_pf(pf_filename,class_number = class_number)

	elif not opt.pfwav is None:
		pf_filename = opt.pfwav
		add_pf_wav_filename(pf_filename)

	elif not opt.clpf is None:
		pf_filename = opt.clpf
		classify_pf_wav_filename(pf_filename)

	elif not opt.clpfdb is None:
		pf_filename = opt.clpfdb
		if not opt.rid is None and not opt.classifn is None:
			recording_id = opt.rid
			if opt.classifn == '1':
				classify_pf_wav_filename_to_db(pf_filename, recording_id)
			elif opt.classifn == '2':
				classify_pf_wav_filename_to_db(pf_filename, recording_id, first_classif=False, second_classif=True)
		else:
			print "Classification data to db requires recording_id and classification number"