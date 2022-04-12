package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class testssafy3 {
	static int R, C;
	static int[][] map;
	static boolean[][] visited;
	static int max = Integer.MIN_VALUE;
	static int[] dx1 = { -1, 0, 1, 1, 0, -1 };
	static int[] dy1 = { 0, -1, 0, 1, 1, 1 };
	static boolean flag;

	// 아래불가
	static int[] dx2 = { -1, -1, 0, 1, 1, 0 };
	static int[] dy2 = { 0, -1, -1, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine().trim());

		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[C][R];
			visited = new boolean[C][R];

			for (int i = 0; i < C; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < R; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 입력 완료

			for (int i = 0; i < C; i++) {
				for (int j = 0; j < R; j++) {
					dfs(i, j, 1, 0);
				}
			}
			System.out.println("#" + t + " " + max * max);
		}
	}
	private static void dfs(int y, int x, int cnt, int sum) {

		if (cnt == 1) {
			sum = map[y][x];
			visited[y][x] = true;
		}
		if (cnt == 4) {
			max = Math.max(sum, max);
			System.out.println("max"+max);
			return;
		}
		//아래 불가 0, 2 ...
		int nx=0, ny=0;
		if(x%2==0) {
		  for (int i = 0; i < dx2.length; i++) {
			  nx = x + dx2[i];
			  ny = y + dy2[i];	
			  if (nx < 0 || ny < 0 || nx >= R || ny >= C ) continue;
			  //if(flag && visited[ny][nx]==true) continue;
				if(cnt==3 && !flag && visited[ny][nx]==true ) {
					flag =true;
					dfs(ny,nx,cnt,sum); 
					flag =false;
				}
				else if(visited[ny][nx]==false){
					visited[ny][nx] = true;
					//flag = false;
					dfs(ny, nx, cnt + 1, sum + map[ny][nx]);
					visited[ny][nx] = false;
				}		 
					
		  }
		}else if(x%2==1){
			for (int j = 0; j < dx1.length; j++) {
				nx = x + dx1[j];
				ny = y + dy1[j];
				if (nx < 0 || ny < 0 || nx >= R || ny >= C ) continue;
				//if(flag && visited[ny][nx]==true) continue;
				if(cnt==3 && !flag && visited[ny][nx]==true ) {
					flag =true;
					dfs(ny,nx,cnt,sum);
					flag =false;
				}
				else if(visited[ny][nx]==false) {
					visited[ny][nx] = true;
					//flag = false;
					dfs(ny, nx, cnt + 1, sum + map[ny][nx]);
					visited[ny][nx] = false;
				}
				
			}	
		}

	}
}
