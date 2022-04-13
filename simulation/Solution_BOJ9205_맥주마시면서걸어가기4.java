package BOJ9205_맥주마시면서걸어가기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_BOJ9205_맥주마시면서걸어가기4 {

	// 편의점 목록을 관리하는 리스트
	static ArrayList<Store> storelist;
	// 맥주 개수
	static int cup;

	static class Store {
		int x, y;

		public Store(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Home {
		int x, y;

		public Home(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	static class Festa {
		int x, y;

		public Festa(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		// 테케 시작
		for (int t = 1; t <= T; t++) {

			// 매 테케마다 편의점 리스트 초기화
			storelist = new ArrayList<Store>();
			// 처음 시작할 때 맥주 20잔
			cup = 20;

			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			// Home home = new Home(10, 20); // static 으로 안해서 에러 뜨는부분

			// 상근이 집 좌표 받기 , 생성자로 바로 값을 받으면서 int 형변환
			Home home = new Home(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// 편의점 좌표 받기 (편의점은 여러개이므로 storelist에서 관리)
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				Store store = new Store(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				storelist.add(store);
			}

			// 축제 좌표 받기
			st = new StringTokenizer(br.readLine(), " ");
			Festa festa = new Festa(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			int size = storelist.size();
			move(home, festa, storelist, size);
		}

	}

	private static void move(Home home, Festa festa, ArrayList<Store> list, int size) {
		//System.out.println("ha");
		while (home.x != festa.x || home.y != festa.y) {

			//편의점 이동
			for (int i = 0; i < size; i++) {
				Store s = list.get(i);
				int disx = Math.abs(home.x - s.x);
				int disy = Math.abs(home.y - s.y);
				//System.out.println("disx" + disx+" disy" + disy);
				
				//System.out.println("앞"+Math.abs(festa.x-home.x));
				//System.out.println(s.x);
				//System.out.println(home.x);
				//System.out.println("뒤"+Math.abs(s.x-home.x));
				if(disx>1000 || disy>1000) continue;
				if((Math.abs(festa.x-home.x) < Math.abs(s.x-home.x)) && (Math.abs(festa.y-home.y) < Math.abs(s.y-home.y))) continue;
				//System.out.println("i"+i);
				//System.out.println("x "+home.x +" y"+ home.y);
				while (disx != 0 || disy != 0) {
					if(cup == 0) {
						System.out.println("sad");
						return;
					}else if (disx != 0) {
						cup--;
						if(disx<50) {
							home.x += disx;
							disx =0;
							break;
						}
						if(s.x>home.x) home.x += 50;
						if(s.x<home.x) home.x -= 50;
						//System.out.println("x3 "+home.x +" y3 "+ home.y);
						//home.setX(home.x + 50);
						disx -= 50;
					} else if (disy != 0) {
						cup--;
						if(disy<50) {
							home.y += disy;
							disy =0;
							break;
						}
						if(s.y>home.y) home.y += 50;
						if(s.y<home.y) home.y -= 50;
					
						//System.out.println("x3 "+home.x +" y3 "+ home.y);
						//home.setY(home.y + 50);
						disy -= 50;
					} 					
					
				}// 편의점 이동(while)
				cup = 20;
				
			} // 편의점 이동 완료, for 종료
			//System.out.println("x2 "+home.x +" y2 "+ home.y);
			int disx = Math.abs(home.x - festa.x);
			int disy = Math.abs(home.y - festa.y);
			//System.out.println("disx" + disx+" disy" + disy);
			while (disx != 0 || disy != 0) {
				if(cup == 0) {
					System.out.println("sad");
					return;
				}else if (disx != 0) {
					cup--;
					if(disx<50) {
						home.x += disx;
						disx =0;
						break;
					}
					if(festa.x>home.x) home.x += 50;
					if(festa.x<home.x) home.x -= 50;
					//System.out.println("x3 "+home.x +" y3 "+ home.y);
					//home.setX(home.x + 50);
					disx -= 50;
				} else if (disy != 0) {
					cup--;
					if(disy<50) {
						home.y += disy;
						disy =0;
						break;
					}
					if(festa.y>home.y) home.y += 50;
					if(festa.y<home.y) home.y -= 50;
					//System.out.println("x3 "+home.x +" y3 "+ home.y);
					//home.setY(home.y + 50);
					disy -= 50;
				} 					
				
			}// 축제이동(while)
			
		} // 축제 도착 (while 종료)
		System.out.println("happy");
	}
}