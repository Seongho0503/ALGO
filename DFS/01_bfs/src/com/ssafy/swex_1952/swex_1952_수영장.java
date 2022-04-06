package com.ssafy.swex_1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class swex_1952_수영장 {
	
	static int[] fee;
	static int[] months;
	static int min;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 테스트케이스 수
		int tc = Integer.parseInt(br.readLine());
		
		// t=0;부터 시작하는데 t<=tc로 하면 
		//java.lang.NumberFormatException: For input string: ""에러 뜬다
		//반복문 돌리기
		for(int t=0; t<tc; t++) {
			// br.readLine().split(" ")은 문자열 배열이다
			//int[] fee = Integer.parseInt(br.readLine().split(" "));에러남
			
			// 문자열을 임시 저장
			String[] temp = br.readLine().split(" "); 
		
			fee = new int[4];
			
			// 이용권 요금 담기
			for(int i=0; i<temp.length; i++) {
				fee[i] = Integer.parseInt(temp[i]);
			}
			
			//월별 이용 계획
			months = new int[12];
			temp = br.readLine().split(" ");
			for(int i=0; i<temp.length; i++) {
				months[i] = Integer.parseInt(temp[i]);
			}
			
			min = Integer.MAX_VALUE;
			dfs(0,0);
			min = min >fee[3] ? fee[3] :min;
			System.out.println("#"+(t+1)+" "+min);	
			//System.out.println("#"+(t+1)+" "+min);
			// 0 11 21 31 41 51.. 이렇게 찍힌다
		}
		br.close();
	}
	private static void dfs(int cnt, int sum) {
		// TODO Auto-generated method stub
		//
		//System.out.println(cnt+" "+sum);
		if(cnt >= 12){
			min = min > sum ? sum: min;
			return;
		}
		
		if(months[cnt]==0) {
			dfs(cnt+1, sum);
		}
		
		else {
			dfs(cnt+1, sum + (months[cnt]*fee[0]));
			dfs(cnt+1, sum + fee[1]);
			dfs(cnt+3, sum + fee[2]);
		}
	}
}
