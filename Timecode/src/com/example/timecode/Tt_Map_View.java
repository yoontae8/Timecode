package com.example.timecode;

import com.example.timecode.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Tt_Map_View extends Activity {

	
	String sp, dp, way="";
	int i, j, k;
	int count;
	int insertcase;// 0�̸� i<j, 1�̸� i>j
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tt_map_view);
		
		Shortestpath shortest= new Shortestpath();
		
		Intent myIntent=getIntent();
		sp=myIntent.getStringExtra("sp");
		dp=myIntent.getStringExtra("dp");
		
		for(i=0; i<21; i++)
			if(sp.equals(shortest.name[i])){
				for(j=0; j<21; j++)
					if(dp.equals(shortest.name[j]))
						break;
				break;
			}
		
		TextView spt = (TextView)findViewById(R.id.SP2);
		TextView dpt = (TextView)findViewById(R.id.DP2);
		
		spt.setText(sp);
		dpt.setText(dp);
		
		
		TextView sc = (TextView)findViewById(R.id.SC2);
		TextView path = (TextView)findViewById(R.id.PATH2);
		
		
		sc.setText( Integer.toString(shortest.distance[i][j])+" �� (minutes)");
		
		way = sp;
		
		if(i<j){
			
			count = shortest.count[i][j];
			for(k=1; k<shortest.count[i][j];k++)
					way=way+" -> port "+Integer.toString(shortest.path[i][j][k]-6);
			
		}else{
			
			count = shortest.count[j][i];
			k=i;
			i=j;
			j=k;
			
			for(k=count-1; k>0 ;k--)
					way=way+" -> port "+Integer.toString((shortest.path[i][j][k]-6));
			
		}
		
		way = way+" -> "+dp;
		
		path.setText(way);
		
		
		
	}
	
	public class Shortestpath {
		
			int i,j;
			int[][] cost = new int[21][21];
			String[] name = {"","Shalom&Evenezel", "Vision", "Creation", "Bethel", "Lothem", "Grace", "", "", "", "",
								"", "", "", "HyoamChaplain", "GLC", "HDH", "NMH", "NTH", "OH","ANH", "SU"};
			int[][] distance = new int[21][21];
			int[][][] path = new int[21][21][10];
			int[][] count = new int[21][21];
		
		
		Shortestpath(){
			

			
			for(i=0;i<21;i++)
				for(j=0;j<21;j++)
					count[i][j]=0;
			
			insertCost(cost);
			allCost(cost, distance, path, count, 21);
			
			for(i=0;i<21;i++){}
//				for(j=0;j<21;j++)
				//	System.out.print();
		}
		
		void insertCost(int[][] cost){
			
			int i, j;
			
			for(i=0;i<21;i++)
				for(j=0;j<21;j++)
					cost[i][j]=1000;
			
			cost[1][7]=1;
			cost[1][11]=2;
			cost[2][7]=1;
			cost[2][11]=2;
			cost[3][7]=2;
			cost[3][11]=1;
			cost[4][7]=2;
			cost[4][11]=1;
			cost[5][11]=1;
			cost[6][11]=2;
			cost[7][8]=2;
			cost[7][11]=2;
			cost[7][14]=1;
			cost[8][9]=2;
			cost[8][14]=1;
			cost[8][15]=1;
			cost[8][16]=2;
			cost[8][10]=5;
			cost[9][16]=3;
			cost[9][10]=2;
			cost[9][12]=1;
			cost[10][16]=3;
			cost[10][17]=1;
			cost[10][13]=1;
			cost[11][20]=2;
			cost[11][12]=3;
			cost[12][20]=2;
			cost[12][13]=2;
			cost[13][18]=1;
			cost[13][19]=1;

			for(i=0;i<21;i++)
				for(j=0;j<21;j++)
					if(i>j)
						cost[i][j]=cost[j][i];
					else if(i==j)
						cost[i][j]=0;
			
		}
		
		void allCost(int cost[][], int distance[][], int path[][][], int count[][], int n){
			int i,j,k,l,m;
			for(i=1; i<n; i++)
				for(j=1;j<n;j++)
					distance[i][j] = cost[i][j];
			
			for(k=1;k<n;k++)
				for(i=1; i<n; i++)
					for(j=1;j<n;j++)
						if(distance[i][k]+distance[k][j]<distance[i][j]){
							distance[i][j] = distance[i][k]+distance[k][j];
							l=1;
							m=1;
							for(;m<count[i][k];m++,l++)
								path[i][j][m]=path[i][k][m];
							
							path[i][j][m] = k;
							m++;
							l++;
							
							for(;m<=l+count[k][j];m++)
								path[i][j][m]=path[k][j][m];
							
							count[i][j]=m-1;
							
						}
		}
		
	}
	
}

