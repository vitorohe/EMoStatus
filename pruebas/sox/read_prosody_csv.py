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

def get_chunks(cuts,min_pause_to_cut,output_dir,same_output=False):
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
			if same_output:
				end_chunk = cuts[num]['end']
			else:
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

	get_cuts(chunks,audio_filename,output_dir,same_output=same_output)

def get_cuts(cuts,audio_filename,output_dir,same_output=False,output_name=None):
	
	total_cuts = len(cuts)

	num_len = 0

	if total_cuts < 1000:
		num_len = 3

	elif total_cuts < 10000:
		num_len = 4
	
	i = 1
	if not path.exists(output_dir):
		makedirs(output_dir)
	
	audio_name = path.basename(audio_filename)

	for cut in cuts:
	
		init = cut['init']
		init = float(init)
		init = divide(init,100.)

		duration = cut['duration']
		duration = float(duration)
		duration = divide(duration,100.)

		str_number = get_str_number(num_len,i)

		if same_output:
			system('sox %s %s%s trim %s %s' % (audio_filename,output_dir,audio_name,init,duration))
		elif not output_name is None:
			system('sox %s %s%s%s.wav trim %s %s' % (audio_filename,output_dir,output_name,str_number,init,duration))
		else:
			system('sox %s %soutput%s.wav trim %s %s' % (audio_filename,output_dir,str_number,init,duration))
		
		i += 1

def get_words(cuts,audio_filename,output_dir,output_name=None):
	
	final_words = []

	for cut in cuts:
	
		duration = cut['duration']
		duration = float(duration)
	
		if duration < 20:
			continue

		# cut['duration'] = 20
		if duration > 80:
			cut['duration'] = 80

		final_words.append(cut)

	get_cuts(final_words,audio_filename,output_dir,output_name=output_name)


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

				while float(spamreader.next()[3]) < 0.55 :
					pass

				init = int(spamreader.line_num) - 1

				while float(spamreader.next()[3]) >= 0.55 :
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
    _opt("--sout", action="store_true", help="Cut phrase from audio file, and output with the same name")
    _opt("--outname", help="Set the output prefix name for output files")

    _cmd_options, _cmd_args = _cmd_parser.parse_args()

    opt, args, parser = _cmd_options, _cmd_args, _cmd_parser

    csv_filename = None
    audio_filename = None
    output_dir = ''
    output_name = None

    if opt.csv:
    	csv_filename = opt.csv

    if opt.au:
    	audio_filename = opt.au

    if opt.outdir:
    	output_dir = opt.outdir
    	if not output_dir.endswith('/'):
    		output_dir = output_dir + '/'

    cuts = process_csv(csv_filename)

    if opt.ch:
    	min_pause_to_cut = 60
    	if opt.sout:
        	get_chunks(cuts,min_pause_to_cut,output_dir,same_output=True)
        else:
        	get_chunks(cuts,min_pause_to_cut,output_dir)
    else:
    	if opt.outname:
    		output_name = opt.outname
    	get_words(cuts,audio_filename,output_dir,output_name=output_name)