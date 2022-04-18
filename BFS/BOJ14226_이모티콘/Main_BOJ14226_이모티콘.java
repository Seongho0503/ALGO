package BOJ14226_이모티콘;

/*
 *  <복기>
 * 출처 : BOJ
 * 번호 : 14226
 * 난이도 : 골5
 * 풀은 날짜 : 04-18 (월)
 * 
 * 1. 문제 분석
 * : 최단거리, 최소시간 -> bfs로 접근해야한다.
 * 	 bfs는 큐에 넣을 것들을 보통 클래스를 정의해서 관리한다.
 * 
 * - 복사
 * - 붙여넣기
 * - 삭제 
 * 
 * 최단시간로 돌릴 려면 복사 -> 붙여넣기 가 맨처음 와야하낟
 * 클립보드에 저장된 것이 없는데 붙여넣기를 해도 아무런 변화가 일어나지 않는다.
 *
 *<방문체크 여부 : 중복여부>
 * 1) 현재 화면 이모티콘
 * 2) 클립보드에 저장된 이모티콘
 * 
 * 
 * 2. 주의할 점
 * 그리고 생각해야 할 점은, 문제에서 이모티콘의 개수를 정의해 주었다는 것이다. 그에 맞춰 적절히 조건을 추가해 주면 된다.
	예를 들어 복사의 경우 최대 이모티콘의 개수보다 두 배 이상 높아질 수는 없을 것이다.
 	붙여넣기 경우 현재이모티콘의 수와 복사한 이모티콘의 수가 최대값을 넘지 않을 것이다.
 	빼기의 경우는 계산 이후 0보다는 큰 값이 나와야 할 것이다.
 * 
 * 
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BOJ14226_이모티콘 {

	// bfs 돌릴 때 현재의 단계에서 상태 표시 클래스
	// 변수명을 진짜 잘 지어야겠다 ( 구별성[차별성], 짧은)
	// 너무 길게 네이밍을 하니 bfs 돌릴 때 힘들었다
	static public class State {
		// 화면에 보이는 이모티콘 개수
		int emoticon_num;
		// 클립 보드에 저장된 이모티콘 개수
		int clip_num;
		// 걸린 시간[흘러간 시간]
		int time;

		// 초기화
		public State(int emoticon_num, int clip_num, int time) {
			super();
			this.emoticon_num = emoticon_num;
			this.clip_num = clip_num;
			this.time = time;
		}
	}

	static int num;
	static boolean[][] visited = new boolean[1001][1001];
	//bfs와 다르게 static을 안 정해줘도 편함
	//static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		bfs();
		// System.out.println(min); bfs에선 메서드 안에서 출력

	}

	private static void bfs() {
		//큐 선언
		Queue<State> q = new LinkedList<>();
		// 현재 화면에 보이는 이모티콘 개수/ 클립보드에 저장된 이모티콘 개수 / 걸린시간
		// 기본 현 상태   를 큐에 넣기
		q.offer(new State(1, 0, 0));
		// int curNode = 0; 이런 느낌

		while (!q.isEmpty()) {
			State now = q.poll();
			
			// 입력받은 이모티콘 개수와 똑같해지면 최소 시간 출력
			if (now.emoticon_num == num) {
				System.out.println(now.time);
				return;
			}
			
			/*System.out.println(now.clip_num);
			System.out.println(now.emoticon_num);
			System.out.println("------------------");*/
			
				// 방문 하지 않은 곳을 탐색 (원래 이거를 클립보드 저장과 삭제에 초기 조건으로 했으나 굳이 필요없음)
				//if(now.emoticon_num >0 && now.emoticon_num < 2000) {
				
			
				// 1. 화면의 이모티콘 복사하여 클립보드에 저장 (while문 돌아갈 때 default 기본 동작)
			    // 방문 여부 체크 후 큐에 넣기	
				if(!visited[now.emoticon_num][now.emoticon_num]) {
					visited[now.emoticon_num][now.emoticon_num] = true;
					q.offer(new State(now.emoticon_num, now.emoticon_num, now.time+1));				
				}
				
			
				//2. 클립보드의 이모티콘을 화면에 붙여넣기
				// 클립에 저장된 것이 하나라도 있어야 하고, 저장된 값이 입력해야할 값보다 크면 안된다(너무 많아짐, 최소시간에서 이탈)  ,방문체크
				//&& now.emoticon_num + now.clip_num <= num
				if(now.clip_num > 0 &&  now.emoticon_num+now.clip_num<= num &&!visited[now.emoticon_num+now.clip_num][now.clip_num] ) {
					visited[now.emoticon_num + now.clip_num][now.clip_num] = true;
					q.offer(new State(now.emoticon_num + now.clip_num, now.clip_num, now.time+1 ));
				}
				
				
				//3. 화면의 이모티콘을 삭제한다 //
				//if( now.emoticon_num + now.clip_num != num &&1<=now.emoticon_num && visited[now.emoticon_num-1][now.clip_num]) {
					
				//now.emotion - 1>0 동일
				if(now.emoticon_num >0 && !visited[now.emoticon_num - 1][now.clip_num]) {
						visited[now.emoticon_num-1][now.clip_num] = true;
				    q.offer(new State(now.emoticon_num-1, now.clip_num, now.time+1));
				}
				
			//}
		}
	}
}
