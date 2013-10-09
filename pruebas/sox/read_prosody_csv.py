#read_prosody_csv.py
import csv
from os import system, path, makedirs
from numpy import mean, divide

def get_str_number(num_len,i):

	i_str = str(i)
	str_number = ''

	for j in range(0,num_len - len(i_str)):
		str_number += '0'

	return str_number + i_str

def get_chunks(cuts,min_pause_to_cut,output_dir):
	pauses = []
	chunks = []
	ch_cuts = []
	ch_cuts.append(cuts[0])

	init_chunk = cuts[0]['init']

	init_pause = cuts[0]['end']+1
	# print init_pause
	i = 1
	# import ipdb; ipdb.set_trace()
	for num in range(1,len(cuts)):
	
		end_pause = cuts[num]['init'] - 1
		pause = {'init':init_pause, 'end':end_pause, 'duration':end_pause - init_pause + 1}
		print pause
		if pause['duration'] >= min_pause_to_cut:
			
			end_chunk = pause['init']-1
			chunk = {'init':init_chunk, 'end':end_chunk, 'duration':end_chunk - init_chunk + 1, 'num_cuts':i, 'cuts':ch_cuts}
				
			# if chunk['duration'] > 700:
			# 	shorter_chunks = get_chunks(chunk['cuts'],min_pause_to_cut - 10)
			# 	chunks.append(shorter_chunks)
			# else:
			# print '\t',chunk
			if chunk['duration'] >= 50:
				print 'init:',chunk['init'],', end:',chunk['end'],', duration:',chunk['duration'],', num_cuts:',chunk['num_cuts']
				chunks.append(chunk)

			i = 0
			init_chunk = pause['end'] + 1
			ch_cuts = []

		i += 1
		ch_cuts.append(cuts[num])
		init_pause = cuts[num]['end'] + 1

	if len(ch_cuts) > 0:
		end_chunk = pause['end']-1
		chunk = {'init':init_chunk, 'end':end_chunk, 'duration':end_chunk - init_chunk + 1, 'num_cuts':i, 'cuts':ch_cuts}
		
		if chunk['duration'] >= 50:
			print 'init:',chunk['init'],', end:',chunk['end'],', duration:',chunk['duration'],', num_cuts:',chunk['num_cuts']
			chunks.append(chunk)

	get_words(chunks,audio_filename,output_dir)

def get_words(cuts,audio_filename,output_dir):
	
	total_cuts = len(cuts)

	num_len = 0

	if total_cuts < 1000:
		num_len = 3

	elif total_cuts < 10000:
		num_len = 4
	
	i = 1
	
	makedirs(output_dir)
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

def process_csv(csv_filename):
	i = 0
	zeros = 0
	real_init = 0
	pauses = []
	cuts = []
	first = True
	with open(csv_filename, 'rb') as csvfile:
		spamreader = csv.reader(csvfile, delimiter=';')
		
		# Jump over the titles
		spamreader.next()

		while True:
			try:

				while float(spamreader.next()[2]) == 0.0 :
					pass

				init = int(spamreader.line_num) - 1

				while float(spamreader.next()[2]) != 0.0 :
					pass

				end = int(spamreader.line_num) - 2

				cut = {}

				cut['init'] = init
				cut['end'] = end
				cut['duration'] = end - init + 1

				cuts.append(cut)

			except Exception:
				break			

	return cuts

if __name__ == '__main__':

	#Command Arguments parser and help generator
    from optparse import OptionParser

    _cmd_parser = OptionParser(usage="usage: %prog [options]")
    _opt = _cmd_parser.add_option
    _opt("--csv", help="Set csv filename to process")
    _opt("--au", help="Set audio filename to cut")
    _opt("--outdir", help="Set output dir where to save audio files")
    _opt("--ch", action="store_true", help="Cut audio file in chunks")

    _cmd_options, _cmd_args = _cmd_parser.parse_args()

    opt, args, parser = _cmd_options, _cmd_args, _cmd_parser

    csv_filename = None
    audio_filename = None
    output_dir = ''

    if opt.csv:
    	csv_filename = opt.csv

    if opt.au:
    	audio_filename = opt.au

    if opt.outdir:
    	output_dir = opt.outdir + '/'

    cuts = process_csv(csv_filename)

    if opt.ch:
    	min_pause_to_cut = 60
        get_chunks(cuts,min_pause_to_cut,output_dir)
    else:
    	get_words(cuts,audio_filename,output_dir)