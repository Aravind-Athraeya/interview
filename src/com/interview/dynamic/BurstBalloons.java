
#include "stdafx.h"
#include <set>
#include <iostream>
#include<conio.h>
#include <sstream>
#include<stdio.h> 
#include<stdlib.h>
#include <queue>
#include <unordered_set>

using namespace std;




////
////index:     0   1   2   3
////       + -------------- - +
////nums : 1 | 3 | 1 | 5 | 8 | 1
////       + -------------- - +

// Inutition//www.youtube.com/watch?v=o3-PUPXiVfI
// Intuition - Think about when the last ballon is burst. 
//Let's say an array -[3,1,5,8] and let assume 3 is the last ballon to be burst and [1,5,8] is already solved to get an optimal value for Max coins.
// During the Last step - 1 [Value form bursting 3 + Optimal value of [1,5,8] 1
// Optimal [Last balloon] =  1*3*1  + Opt[1,5,8] or in case on index 1*3*1  + Opt[1,3]
// Optimal [Last balloon] = Max of { 1*3*1  + Opt[1,3],1*1*1  + Opt[0,0] + Opt[2,3],1*5*1 + Opt[0,1] + Opt[3,3],1*8*1  + Opt[0,2]}
//Opt[1,3] = Max of {  3 *  1 * 1 + Opt[2,3], 3 * 5 * 1 + Opt[1,1 + Opt[3,3]], 3 * 8 * 1 + Opt[1,2]}//
 // Full analysis - https://drive.google.com/open?id=0B3AN8dMNKcWVenUwTGN1bDlQVXVsMUhlUHBCRkZHc2x6WVhr
// Fill up the diagnol elements first , so other values can be expressed as a function of that to find the maximum


// Step 1: Find the dignal elements values
// Use to the diagnol element values to find the other va

#define M 4

int BurstBallons(int balloons[], int length)
{

	int B[M + 2] = {0};

	B[0] = 1; // One before the array
	B[M + 1] = 1; // One after the array

	// Copy values from A to B

	for (int i = 1; i <= M; i++)
		B[i] = balloons[i - 1];



	// Algorthim


	// Declare DP Array  [1,3,1,5,8,1]
	int MaxGain[M + 2][M + 2] = {0};

	 // Fill the diagonal elements
	int counter = 1;
	while (counter <= M)
	{
		// Degree one
		MaxGain[counter][counter] = B[counter - 1] * B[counter] * B[counter + 1];
		counter += 1;
	}

	//  optmization equation
   //	First order filling -- 11,22,33,44
   //   Second order Filling --  adjacent layers to diagonal 
	//	MaxGain[i][j] = MaxOf{ B[i - 1] * B[i] * B[i + 1] + MaxGain[i][i], B[j - 1] * B[j] * B[j + 1] + MaxGain[j][j] }

	// 
	for (int i = 1; i < M - 2; i++)
	{ 
		int order = 0;
		for (int degree = 1; degree <= M - 1; degree++) 
		{
			int offset = degree - 1, MaxGainTillNow = 0;
			for (int j = 0; j <= i; j++) 
			{							
				int c = B[i - 1 + offset] * B[i + j + offset] * B[i + offset + 2] + MaxGain[i + degree - j][i + degree - j];
				if (MaxGainTillNow < c)
					MaxGainTillNow = c;
			}
			order = order + 1;
			MaxGain[order][order + i] = MaxGain[order + i][order] = MaxGainTillNow;
			
			// Updating both sides of the diagonal

		}



		}



		
	////
	////index:  0  1   2   3   4  5
	////       + -------------- - +
	////nums : 1 | 3 | 1 | 5 | 8 | 1
	////       + -------------- - +




	MaxGain[counter][counter];
	
	return MaxGain[1][M];


}


int main()
{
	int A[] = { 3,1,5,8};

	// Size of the array 
	int N = sizeof(A) / sizeof(A[0]);

	// Calling function 
	cout << BurstBallons(A, N) << endl;
	return 0;

}
