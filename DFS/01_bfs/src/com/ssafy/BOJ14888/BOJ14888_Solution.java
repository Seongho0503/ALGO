package com.ssafy.BOJ14888;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14888_Solution {
	static int N;
	static int[] arr;
	static int[] cal = new int[4];
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	//static int idx;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 숫자의 개수 입력
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		// 배열에 값 담기
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//연산자 받기
		
		st = new StringTokenizer(br.readLine()," ");
		for (int j=0; j<4; j++) {
			cal[j]= Integer.parseInt(st.nextToken());
		}
		dfs(0, 0);
		System.out.println(max);
		System.out.print(min);

	}
	private static void dfs(int idx, int sum) {
		// 인덱스 범위가 배열의 크기를 넘어가면 기존의 sum과 크기 비교
		// idx>=N 해도 상관 없음
		
		if(idx+1==N) {
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			return;		
			}
		if(idx==0) sum = arr[0];
		//System.out.println("sum은 뭐니"+sum);
		for(int i=0; i<4; i++) {
			
			//연산자 하나씩 선택하여 보내기
			if(cal[i]>0) {
				cal[i]--;
				//System.out.println("어떤 연산자"+i);
				switch(i){
				
				case 0: dfs(idx+1, sum+arr[idx+1]); break;
				case 1: dfs(idx+1, sum-arr[idx+1]); break;
				case 2: dfs(idx+1, sum*arr[idx+1]); break;
				case 3: dfs(idx+1, sum/arr[idx+1]); break;
					
				}
				//연산하고서 나올 때는 다른 dfs도 써야하니깐 개수 다시 늘리기
				cal[i]++; 
			}
		} // for문 종료
		
	}
}
