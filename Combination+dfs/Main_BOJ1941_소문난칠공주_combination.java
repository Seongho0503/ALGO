package BOJ1941_소문난칠공주;


	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.LinkedList;
	import java.util.Queue;

	/*
	 * 0  1  2  3  4
	 * 5  6  7  8  9
	 * 10 11 12 13 14
	 * 15 16 17 18 19
	 * 20 21 22 23 24
	 * 
	 * 
	 */
	public class Main_BOJ1941_소문난칠공주_combination {
		static char map[][] = new char[5][5];
		static int result;
		static int sel[] = new int [7];
		static void comb(int cnt , int idx, int Y) {
			
			// 이다솜파가 4명이상 되야하기 때문에 (임도연파 친구가 3명이상되면 안 됨) 
			if(Y>3)
				return;
			// 7공주 완성
			if(idx == 7) {
				// 인접 여부 체크 
				if(check()) {
					result++;
				}
				return;
			}
			//25명 반 종료
			if(cnt == 25)
				return;
			
			// 뽑힌 학생 번호 입력
			sel[idx] = cnt;
			// 뽑힌 것이 Y인지 확인법
			int isY = map[cnt/5][cnt%5] == 'Y'? 1 : 0;
			comb(cnt+1,idx+1,Y+isY);
			comb(cnt+1,idx,Y);
			
			/*
			  - - - - - - -
			  0 1 2
			      3
			    2 3
			  1 2 3
			  
			 */
			
		}
		static boolean check() {
			//뭐든 요소가 연결되어있는지 확인
			boolean [] visit = new boolean [7];
			visit[0] =true;
			Queue<Integer> que = new LinkedList<Integer>();
			que.offer(0);
			int cnt = 1;
			while(!que.isEmpty()) {
				int i = que.poll();
				for(int j = 0 ; j < 7 ; j++) {
					if(i==j || visit[j])
						continue;
					// 가장 왼쪽 벽일때
					if(sel[i] % 5 == 0) 
						if(sel[i] - 1 == sel[j])
							continue;
					//가장 오른쪽 벽일때
					if(sel[i] % 5 == 4) 
						if(sel[i] + 1 == sel[j])
							continue;
					if(sel[i] - 1 == sel[j] || sel[i] + 1 == sel[j] || sel[i]+5 == sel[j] || sel[i] - 5 == sel[j]) {
						que.offer(j);
						visit[j] = true;
						cnt++;
					}
				}
			}
			if(cnt != 7) //연결이 하나라도 안되어있으면
				return false;
			
			return true; 
		}
		
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for (int i = 0; i < 5; i++) {
				String line = br.readLine();
				for (int j = 0; j < 5; j++) {
					map[i][j] = line.charAt(j);
				}
			}
			comb(0,0,0);
			System.out.println(result);
			
		}
	}

