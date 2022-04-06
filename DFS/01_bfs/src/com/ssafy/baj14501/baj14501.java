package com.ssafy.baj14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class baj14501 {

	static int[] days; // 몇일 걸리는지
	static int[] pays; // 얼마 주는지
	static int cnt; // 퇴사날
	static int T;
	static int max;
	static int sum;
	/*
	 * 1. 저거 input 어떤 자료구조에 담을까? 1차원 배열 2개 써서
	 * 2. bfs 구현
	 * 
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		// 이거 안해서 NULLPOINTException 떴다. (객체를 안만듬, new)
		days = new int[T];
		pays = new int[T];
		
		// input 값을 다 남아놓음
		for(int i=0; i<T ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			days[i] = Integer.parseInt(st.nextToken());
			pays[i] = Integer.parseInt(st.nextToken());
			
		}
		
		//int 를 2번 선언하면 에러가 나더라고 static int max랑 main max 2개 상충하면 마지막 선언으로 결정
		max = Integer.MIN_VALUE;
		dfs(0,0);
		System.out.println(max);

	}
	// 탐색
	private static void dfs(int cnt, int sum) {
		// 기저조건 = 탈출 조건
		if(cnt >=T) {
			//max = max > sum? max:sum;
			max = Math.max(max, sum);
			return; 
		}

		// 그 해당 날짜 넣기
		if(cnt + days[cnt] <= T) dfs(cnt+days[cnt], sum+pays[cnt]);
		// 퇴사 날이 지나가면 페이를 더하지 않고 날짜만 셈
		else dfs(cnt+days[cnt], sum);
		// 그 해당 날짜 안 넣기
		dfs(cnt+1, sum);
	}
		
}
