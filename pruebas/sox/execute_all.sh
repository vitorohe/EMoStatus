./create_model_from_folders.sh ../samples/training/man/
./create_model_from_folders.sh ../samples/training/woman/
./create_model_from_folders.sh ../samples/training/woman_man/
./test_model_folders.sh ../samples/test/man/ ../samples/training/woman_man/woman_man-rbf3.lsvm.model 1
./test_model_folders.sh ../samples/test/man/ ../samples/training/man/man-rbf3.lsvm.model 1
./test_model_folders.sh ../samples/test/woman/ ../samples/training/woman/woman-rbf3.lsvm.model 1
./test_model_folders.sh ../samples/test/woman/ ../samples/training/woman_man/woman_man-rbf3.lsvm.model 1
python read_predict.py --pf ../samples/test/woman/andrea/andrea-woman-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/andrea/andrea-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/mama/mama-woman-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/mama/mama-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/marce/marce-woman-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/marce/marce-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/noe/noe-woman-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/noe/noe-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/tamara/tamara-woman-rbf3-predict 
python read_predict.py --pf ../samples/test/woman/tamara/tamara-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/gust/gust-man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/gust/gust-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/ivan/ivan-man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/ivan/ivan-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/mati/mati-man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/mati/mati-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/papa/papa-man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/papa/papa-woman_man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/vito/vito-man-rbf3-predict 
python read_predict.py --pf ../samples/test/man/vito/vito-woman_man-rbf3-predict 

