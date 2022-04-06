package com.ssafy.BOJ1330;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1330 {
	static char[][] map;
	static boolean visited[][];
	// 가로, 세로
	static int cnt;
	static int R, C; 
	static int wcnt, bcnt;
	// 대각선은 포함 아니키니 4방탐색
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[C][R];
		visited = new boolean[C][R];
		// 배열 담기
		
		//처음에 가로세로 잘못썼네
		for(int i= 0; i<C; i++) {
			//st= new StringTokenizer(br.readLine());
			String str = br.readLine();
			for(int j=0; j<R; j++) {
				//map[C][R] = st.nextToken().charAt(0);
				map[i][j] = str.charAt(j);
			}	
		}//배열 담기 end	
		// dfs 
		
		for(int i=0; i<C; i++) {
			for(int j=0; j<R; j++) {
				cnt = 1;
				//완전 탐색 (방문여부)
				if (!visited[i][j]) {
				dfs(map[i][j],i,j);
			
				if(map[i][j]=='W') wcnt+= cnt*cnt;
				else bcnt+= cnt*cnt;
				}
	       }
		}
		System.out.println(wcnt+ " "+bcnt);
		// 모두 visted 했는지 체크 디버깅
		//System.out.println(Arrays.deepToString(visited)); 
	}
	private static void dfs(char color, int x, int y) {

		visited[x][y] = true;
		
				//for문이 기저조건을 주었다 
				for (int i = 0; i < dx.length; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					// map 유효성 검사 (map 범위)
					// 초반에 && 써서 에러찾음
					if (nx < 0 || ny < 0 || nx >= C || ny >= R )  continue;	
					// 방문여부 (똑같은 곳 방문했을 때 개수 셀 필요 없으니깐)
					// 이거 근데 주의할점이 visited[nx][ny]를 위의 조건 앞에 쓰면 ArrayIndexOutofBoundads에러남 다른 줄에서 검사하거나
					// 마지막에 해줘야함
					if (visited[nx][ny]) continue;
					// 같은 색 먼저 dfs (4방탐색이 계속 이어질 때까지)
					if (map[nx][ny] != color) continue;		
					// visited[x][y] = true; 이거를 여기다 두면 안되는 이유 
					cnt++;
					// 처음에 x,y로 했다가
					dfs(color,nx,ny);
				}
			}
	
		} // 탐색종료 종료

