package BOJ5430_AC;


	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Deque;
	import java.util.LinkedList;
	import java.util.StringTokenizer;

	public class AC_SOL {

		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int T = Integer.parseInt(br.readLine());
			
			for(int tc=0; tc<T; tc++) {
				char[] func = br.readLine().toCharArray();
				int len = Integer.parseInt(br.readLine());
				
				String s = br.readLine();
				//[ 와 ] 버리기 , 파싱 
				s = s.substring(1, s.length()-1);
				Deque<String> q = new LinkedList<>();
				
				StringTokenizer st = null;
				if(len != 0) {
					// ,을 구분자로 토큰화 해서 큐에 입력
					st = new StringTokenizer(s, ",");
					for(int i=0; i<len; i++)
						q.offer(st.nextToken());
				}
				
				boolean isReverse = false;
				boolean isError = false;
				for(int i=0; i<func.length; i++) {
					if(func[i] == 'R')
						// 방법3) 이걸 생각 못했네
						isReverse = !isReverse;
					/* 방법1)
					 * if(flag) {
						flag = false;  //Reverse();
						}else flag = true;*/
				    
					// 방법2) flag = flag==true ? false : true;
					else {
						if(q.isEmpty()) {
							isError = true;
							break;
						}
						if(isReverse) q.pollLast();
						else q.poll();
					}
				}
				
				StringBuilder sb = new StringBuilder();
				sb.append("[");
				if(isError) System.out.println("error");
				else {
					if(isReverse) {
						while(!q.isEmpty()) sb.append(q.pollLast()).append(",");
					}
					else {
						while(!q.isEmpty()) sb.append(q.poll()).append(",");
					}
					// 마지막 콤마 없애기
					if(sb.length() > 1) sb.setLength(sb.length()-1);
					sb.append("]");
					System.out.println(sb);
				}
			}
		}
	}

