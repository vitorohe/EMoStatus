//read_prosody_csv.cpp
#include <cstdlib>
#include <iostream>
#include <string>
#include <fstream>
#include <sstream>

using namespace std;

int main(int argc, char const *argv[])
{
	const char* filename = argv[1];
	cout << "Reading file " << filename << endl;

	ifstream file (filename);
	string value;
	int i = 0, j = 1;
	getline ( file, value); 
	int zeros = 0;
	while ( file.good() )
	{
		cout << "Reading line " << endl;
	    getline ( file, value, ';' ); 
	    //getline(file, value);
	    //cout << string( value, 1, value.length()-2 ); 
	    if(i == 4){
	    	i = 0;
	    	j++;
	    //	cout << "new line " << value.find("\n") <<endl;
	    	value = value.substr(value.find("\n") + 1); 
	    }
	    
	    //cout << value << " size " << value.size() << endl;
	    //else
	    // cout << i << " ";
		
		
		//if(j == 1)
		//	continue;
	    float f;
	    if(i == 0)
	    	// cout << "Frame " << value << " / ";
	    if(i == 2){
	    	cout << "F0 " << value << endl;
			// istringstream(value) >> f;
	    	cout << f << endl;
	    	// if(f == 0.0)
	    	// 	zeros++;
	    	// else if(zeros > 0){
	    	// 	cout << "Pause long " << zeros <<endl;
	    	// 	zeros = 0;
	    	// }

	    }
		
	    i++;

	    // if(j == 3)
	    // 	break;
	}
	return 0;
}
