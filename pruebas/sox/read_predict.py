#read_prosody_csv.py
import csv
from os import system, listdir, path
from numpy import mean, divide, round

def get_str_number(num_len,i):

	i_str = str(i)
	str_number = ''

	for j in range(0,num_len - len(i_str)):
		str_number += '0'

	return str_number + i_str

def get_chunks(cuts,min_pause_to_cut):
	pauses = []
	chunks = []
	ch_cuts = []
	ch_cuts.append(cuts[0])

	init_chunk = cuts[0]['init']

	init_pause = cuts[0]['end']+1
	# print init_pause
	i = 1
	import ipdb; ipdb.set_trace()
	for num in range(1,len(cuts)):
	
		end_pause = cuts[num]['init'] - 1
		pause = {'init':init_pause, 'end':end_pause, 'duration':end_pause - init_pause + 1}
		
		if pause['duration'] >= min_pause_to_cut:
			
			end_chunk = pause['init']-1
			chunk = {'init':init_chunk, 'end':end_chunk, 'duration':end_chunk - init_chunk + 1, 'num_cuts':i, 'cuts':ch_cuts}
			if chunk['duration'] > 700:
				shorter_chunks = get_chunks(chunk['cuts'],min_pause_to_cut - 10)
				chunks.append(shorter_chunks)
			else:
				print 'init:',chunk['init'],', end:',chunk['end'],', duration:',chunk['duration'],', num_cuts:',chunk['num_cuts']
				print '\t',pause
				chunks.append(chunk)

			i = 0
			init_chunk = pause['end'] + 1
			ch_cuts = []

		i += 1
		ch_cuts.append(cuts[num])
		init_pause = cuts[num]['end'] + 1

	# get_words(chunks,audio_filename,output_dir)

def get_words(cuts,audio_filename,output_dir):
	
	total_cuts = len(cuts)

	num_len = 0

	if total_cuts < 1000:
		num_len = 3

	elif total_cuts < 10000:
		num_len = 4
	
	i = 1
	
	for cut in cuts:
	
		init = cut['init']
		init = float(init)
		init = divide(init,100.)

		duration = cut['duration']
		duration = float(duration)
		duration = divide(duration,100.)

		str_number = get_str_number(num_len,i)

		system('sox %s %soutput%s.wav trim %s %s' % (audio_filename,output_dir,str_number,init,duration))
		
		i += 1

def get_dir_wav_files(predict_path):
	predict_dir = path.dirname(predict_path)
	dir_files = listdir(predict_dir)
	wav_files = []

	for wfile in dir_files:
		if wfile.endswith('.wav'):
			wav_files.append(predict_dir + '/' + wfile)

	wav_files = sorted(wav_files)
	return wav_files

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
    
    _cmd_options, _cmd_args = _cmd_parser.parse_args()

    opt, args, parser = _cmd_options, _cmd_args, _cmd_parser

    pf_filename = None
    class_number = 0

    if opt.pf:
    	pf_filename = opt.pf

    if opt.cn:
    	class_number = opt.cn

    process_pf(pf_filename,class_number = class_number)