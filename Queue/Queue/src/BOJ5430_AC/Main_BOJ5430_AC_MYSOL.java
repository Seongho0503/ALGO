package BOJ5430_AC;



/* <복기>
 * 출처 : BOJ
 * 번호 : 5430
 * 난이도 : 골5
 * 풀은 날짜 : 04-15 (금)
 * 
 * 1. 문제 분석 : 덱을 활용해서 푸는 문제였다. 큐 양 쪽 끝에서 출력이 가능한 형태이기 때문에, 크기가 유동적
 * 
 * 
 * *     Deque : Double-Ended-Queue
 *           : 큐의 양쪽에 데이터를 넣고 뺄 수 있는 자료구조
 *           : 큐 또는 스택으로도 사용할 수 있다.
 *           
 *           
 *              <추가 함수>  : 방향 매우 중요!!!!!!
 *           _______________
    push--->|_____deque____| <--- offer
 * 
 * "offer 만을 이용하여 데이터를 입력하면 큐로 동작[FIFO : First-in, first-out]"
 * "push만 이용하면 stack처럼 동작[LIFO : Last-in, first-out]"
 * 
 *      		<출력 함수>       
 *  pop     _______________
    poll--->|_____deque____| <--- lastpoll
 * 
 * 
 * 
 * 2. 난관 : 처음에 Reverse() 함수를 구현할 때 큐를 swap 큐를 만들어서 값을 저장했다가 다시 이동시키면서 
 * 		   Reverse를 시켰는데 시간초과가 떴다. 그래서 생각해보니 reverse는 서로 출력 방향만 정해주면 되므로
 * 		     투 포인터를 활용해서 풀이했다. 처음 접근할 때는 R이 나오면 reverse() 시켜줘야지 생각했는데 
 *         어? 잠시만 출력할 때만 R이 출력 방향을 지정해 주므로  flag 변수를 만들어줘서 출력 방향이 정방향인지, 역방향인지만
 *         알려줄 수 있도록 설정했다.          
 *         
 *         투포인터 알고리즘(Two Pointers Algorithm) 또는 슬라이딩 윈도우(Sliding Window)라고 부른다. 
 *         투 포인터는 말 그대로 두 개의 포인터를 조작하면서 두 포인터가 가리키는 값이 특정한 조건을 만족하도록,
		      또는 두 포인터 사이의 구간이 조건을 만족하도록 하여 문제를 푸는 방식입니다.
 * 
 * 
 * 3. 활용 : 
 * 
 * 
 * 1) 파싱 방법 (2가지)
 * 
 * String label = null;  ***********
 * 	for(int i =0; i<arr.length(); i++) {
				if(arr.charAt(i)=='[') continue;                  
				// ,나 ] 이면 이전에 label 저장해놓은 char를 integer로 형변환 한 뒤 큐에 넣기 
				if(arr.charAt(i)==',' || (arr.charAt(i)==']' && i>1 )) { //i>1 ////arr.length()>2
					deque.offer(Integer.parseInt(label));
					label = null;
				// 23 234 두 자리나 세 자리 char 숫자를 연결하기 위해
				}else if (label==null) {
					label = ""+arr.charAt(i);   *********** char 숫자을 String으로
				}else label = ""+label+ arr.charAt(i); **********
				
			}// 큐에 값 넣기
 * 
 * 
 * 
 * 
 * //[ 와 ] 버리기 , 파싱 ->subString 
	s = s.substring(1, s.length()-1);
 * StringTokenizer st = null;
	if(len != 0) {
		// ,을 구분자로 토큰화 해서 큐에 입력
	st = new StringTokenizer(s, ",");
	for(int i=0; i<len; i++)
		q.offer(st.nextToken());
		}
 * 
 * 
 * 2) 투 포인터 출력 방향 전환
 * 
 * isReverse = !isReverse;
 * 
 * 
 * 3) 최종 출력할 때 마지막 콤마 없애기   sb는 StringBuilder임
 * if(sb.length() > 1) sb.setLength(sb.length()-1);
 * 
 * 
 * 4. 느낀점 : 요즘 시간 초과 뜨는 경우가 많은데 빨리 내가 잘못 접근한 부분을 찾고 수정하는 것이 중요하다.
 *           처음부터 시간초과가 안뜨게 시간복잡도를 계산해봐야겠다... 
 *           입력값 파싱할 때도 처음에는 char 숫자를 23, 356, 두 세자리를 String으로 이어붙였는데
 *           Stringbuilder , 구분자로 빠르게 파싱하자. 
 *           reverse()도 따로 메서드 구현필요없이 투 포인터로 출력 방향만 체크해주자.  
 * 
 * 
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_BOJ5430_AC_MYSOL {
	
	// 입력값을 파싱한 것을 담는 덱큐
	static Deque<Integer> deque;
	// 투 포인터, 덱큐 방향 바꾸기 Reverse()
	static boolean flag;
	// error 여부 체크
	static boolean error;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			// 전역변수 static 은 꼭 테케에서 초기화해주자, 테케 시작 때 꼭 전역변수 초기화
			flag = false;
			error = false;
			StringBuilder st = new StringBuilder(); 
			//명령어 저장 ex)RRD
			String str = br.readLine();
			
			// 숫자의 개수 num 굳이 필요없어서 버림
			/*int num =*/ br.readLine();// Integer.parseInt(br.readLine());
			
			deque = new ArrayDeque<>();
			
			// 입력값 파싱한 숫자 만들 변수 
			String label = null;
			
			//  ex) [1,123,4,6] 입력
			String arr = br.readLine();
			
			for(int i =0; i<arr.length(); i++) {
				if(arr.charAt(i)=='[') continue;                  
				// ,나 ] 이면 이전에 label 저장해놓은 char를 integer로 형변환 한 뒤 큐에 넣기 
				if(arr.charAt(i)==',' || (arr.charAt(i)==']' && i>1 )) { //i>1 ////arr.length()>2
					deque.offer(Integer.parseInt(label));
					label = null;
				// 23 234 두 자리나 세 자리 char 숫자를 연결하기 위해
				}else if (label==null) {
					label = ""+arr.charAt(i);
				}else label = ""+label+ arr.charAt(i);
				
			}// 큐에 값 넣기
			
			// 큐에 담긴 값 디버깅용
			/*for(int i : deque)
				System.out.printf("%d ", i);
			System.out.println();*/
			
			
			for(int j=0; j<str.length(); j++) {
				//R을 반나면 출력하는 방향 전환
				if(str.charAt(j)=='R') {
					/*if(flag) {
						flag = false;  //Reverse();
					}else flag = true;*/
					flag = flag==true ? false : true;
				//D을 만나면 방향에 따라 제거
				}else {
					Delete();
				}
			}	
			//System.out.println(error);
			
			if(!error) {
					int desize = deque.size();
					st.append("[");
					while(!deque.isEmpty()) {		
						for(int i=0; i<desize; i++) {
							// flag 방향에 따라 출력
							if(flag) {
							  st.append(deque.pollLast());
							}else st.append(deque.poll());							
							if(i!=desize-1) st.append(",");
						}
						
					}
					st.append("]");
				}else st.append("error");
				System.out.println(st);
		
		}//T 종료	
	}

	private static void Delete() {
		if(deque.size()>0) {
			if(flag) {
				deque.pollLast();
			}else deque.poll();
			//(flag==true) ? deque.pollLast() : deque.poll();
		}else {
			error= true;	
			//return; // 여기 return 있으나 마나 둘 다 통과던데 차이가 뭘까??
		}
		
	}
}

// 처음에 바보같이 reverse()할 때 다른 큐에 저장해서 시간초과걸렸는데 투 포인터로 방향만 바꿔주니 통과했음
	/*private static void Reverse() {

		for(int i=0; i<size; i++) {
			//deque 앞에서 빼서 앞에서 넣기
			store.push(deque.poll());
		}
		
		for(int i=0; i<size; i++) {
			deque.offer(store.poll());
		}*/
		
	//}
