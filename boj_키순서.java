package com.ssafy.baj_키순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_키순서 {


	static int N, M; // N : 학생들의 수, M : 두 학생의 키를 비교한 횟수
	static int INF = 987654321;
	static int[][] matrix;
	
	public static void main(String[] args) throws IOException {
		input();
		
		// 플로이드 워샬
		for(int k = 0; k < N; k++) { // 경유지
			for(int i = 0; i < N; i++) { // 출발지
				for(int j = 0; j < N; j++) { // 도착지
					if(matrix[i][j] > matrix[i][k] + matrix[k][j]) // 1-4는 INF였지만 1-4-5가 가능해짐
						matrix[i][j] = matrix[i][k] + matrix[k][j];
				}
			}
		}
		
		int answer = 0;
		for(int i = 0; i < N; i++) { // i번째 학생의 순서 체크
			boolean know = true;
			for(int j = 0; j < N; j++) { // i번째 학생과 j번째 학생의 키 순서 비교
				if(i == j) continue;
				if(matrix[i][j] == INF && matrix[j][i] == INF) // 다른 학생와의 키 순서를 모르면 자신의 순서를 알 수 없음
					know = false; 
			}
			if(know) answer++;
		}
		System.out.print(answer);
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		matrix = new int[N][N]; // N명의 학생들의 키 비교 -> (a < b)이면  matrix[a][b] = 1;
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(matrix[i], INF);
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int stdA = Integer.parseInt(st.nextToken()); // stdA < stdB 
			int stdB = Integer.parseInt(st.nextToken());
			matrix[stdA-1][stdB-1] = 1; 
		}
	}
}