package com.ssafy.BOJ17070_파이프옮기기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17070_Main_파이프옮기기_골5up {
	static int N;
	static int cnt;
	static int[][] house;
/*	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};*/
	//static int nx, ny;
	// 파이프의 상태 1: 옆으로 누운  2: 아래  3: 대각선 
	static int[] con = {1, 2, 3};
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		house = new int[N][N];
		for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<N; j++) {
			house[i][j] = Integer.parseInt(st.nextToken());
			}
		} // for문 종료

		// (파이프 앞 머리 좌표) x ,y, 파이프 상태 
		dfs(1,0,1);
		//System.out.println(Arrays.deepToString(house)); 인풋 디버깅 
		System.out.println(cnt);
	}

	private static void dfs(int x, int y, int c) {
		
		//System.out.println("x"+x+" y"+y+" c"+c); 탐색 디버깅
		//유효성 검사 (배열값은 ArrayOutofIndex 뜰 수 있으니 맨 뒤에)
		if(x<0 || y<0 || x>= N || y >=N ||house[y][x]==1) return;
		// 대각선 파이프 상태일 때 자리 검사
		if(c==3 && (house[y-1][x]==1 || house[y][x-1]==1)) return;
		
		// 모서리 끝 부분에 가는 순간 기저조건 끝(개수 증가)
		if(x==N-1 && y==N-1) {
			cnt++;
		}
	
		// 파이프의 상태 1: 옆으로 누운  2: 아래  3: 대각선 
		switch(c) {
		case 1:
			dfs(x+1,y,1); 
			//System.out.println("아"); 디버깅
			dfs(x+1,y+1,3); break;
		case 2:
			dfs(x,y+1,2); 
			dfs(x+1,y+1,3); break;
		case 3:
			dfs(x+1,y,1); 
			dfs(x,y+1,2);
			dfs(x+1,y+1,3); break;
			
		}		
	}
}
