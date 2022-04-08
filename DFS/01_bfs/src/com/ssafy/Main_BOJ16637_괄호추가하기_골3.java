package com.ssafy.BOJ16637_괄호추가하기;

// 코드 더려워서 주석이랑 변수 다시 정리해서 올릴게요  88%
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_BOJ16637_괄호추가하기_골3 {
	static char[] str;
	static int N;
	static int max = Integer.MIN_VALUE;
	// static int[] temp;
	// static char cal;
	static int calint;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		str = new char[N];
		// 배열에 input 담기
		String st = br.readLine();
		for (int i = 0; i < N; i++) {
			str[i] = st.charAt(i);
		} // for문 종료

		/*
		 * input은 잘 들어갔고 [디버깅용] for(int i=0; i<N; i++) { System.out.println(str[i]); }
		 */
		dfs(0, 0);
		System.out.println(max);

	}

	// idx : 인덱스 / cnt : 괄호 개수/whe : 괄호 위치(앞) / sum : 총합
	private static void dfs(int idx, int sum) {

		if(N==1) {
			max = str[0]-'0';
			return;
		}
		
		// 기저 조건(탈출조건)
		if (idx + 1 >= N) {
			max = Math.max(max, sum);
			return;
		}
		
		// sum 처음 값 초기화
		if (idx == 0) {
			// str은 char 배열이였지 int 전환
			sum = str[idx] - '0';
		}
		
		 //System.out.println("idx "+ idx + "sum " +sum); //중간마다 디버깅
		// System.out.println("괄호"+calculate((str[idx+2]-'0'), (str[idx+4]-'0'), str[idx+3]));
		
		
		
		// a : 앞 피연산자 [숫자] , b : 뒤 피연산자[숫자], c : 연산자
		//calculate(1, 2, '*');
		// 바로 뒤 괄호를 만들 때
		if(idx+4<=N)
		dfs(idx+4, calculate(sum,(calculate((str[idx+2]-'0'), (str[idx+4]-'0'), str[idx+3])), str[idx+1]) );

		// 괄호를 만들지 않을때   sum : calculate(str[idx], str[idx+2], str[idx+1])
		if(idx+2<=N)
		dfs(idx+2, calculate(sum, (str[idx+2]-'0'), str[idx+1]));
	}

	// (피연산자[숫자a] + 연산자 + 피연산자[숫자b]) 결과 return
	private static int calculate(int sum, int numchar2, char cal) { //char numchar1
		int num1 = sum;//numchar1 - '0';
		int num2 = numchar2; // - '0';
		switch (cal) {
		case '+': return (num1 + num2); 
		case '-': return (num1 - num2); 
		case '*': return (num1 * num2);
		// 처음에 default을 설정안해서 에러가 떴음. 물론 cal은 다른 연산자가 나오는 경우가 없지만 함수입장에서 보면 cal이 뭐가 
		//들어올지 몰라 default 넣어야 함. 저는 디버깅 보기 편하게 0을 반환.
		default : return 0;
		}
	}
}