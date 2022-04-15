package Practice_ArrayDeque;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 
 *  - 자료구조에서 큐는 FIFO(First In First Out) 자료구조이다
 *  - 자바의 util에는 기본적으로 큐를 제공하기 때문에 해당 부분을 이용하면 된다
 *  - ArrayDeque 를 이용하면 되는데 주의할 점
 * 
 *  - 데이터를 입력하는 방법에 offer(), push() 두가지 메소드가 있는데
  	  push는 데이터를 앞으로 입력하고, offer는 데이터를 뒤로 입력한다.
      push 만을 이용하여 데이터를 입력하면 스택처럼 동작하고, offer 만을 이용하여 데이터를 입력하면 큐로 동작한다.
            따라서 데이터를 입력할 때 메소드를 잘 선택하여 입력해야 한다.
            
      
 *     Deque : Double-Ended-Queue
 *           : 큐의 양쪽에 데이터를 넣고 뺄 수 있는 자료구조
 *           : 큐 또는 스택으로도 사용할 수 있다.
 *           
 *           
 *              <추가 함수>       
 *           _______________
    push--->|_____deque____| <--- offer
 * 
 * 
 * 
 * 
 *      		<출력 함수>       
 *  pop     _______________
    poll--->|_____deque____| <--- 
 * 
 * 
 * 
 */
public class ArrayDeque_ex {

	public static void main(String[] args) {
		
		System.out.println("offer 만을 이용하여 데이터를 입력하면 큐로 동작[FIFO : First-in, first-out]");
		System.out.println("---------[offer <==]----------");
		//offer를 이용한 데이터 입력
		
		//Deque<Integer> deque =new ArrayDeque<Integer>();
		Deque<Integer> deque = new ArrayDeque<>();
		
		deque.offer(5);
        printDeque(deque);
        deque.offer(4);
        printDeque(deque);
        deque.offer(3);
        printDeque(deque);
        deque.offer(2);
        printDeque(deque);
        deque.offer(1);
        printDeque(deque);

        //5출력
        System.out.println("데큐 사이즈 : "+deque.size());
        
        /*
         i가 증가하면서 deque 사이즈가 계속 변하니까 5개가 다 안 사라짐
                  큐 사용할 땐 항상 사이즈에 조심하자 (큐의 사이즈는 갈대다. 계속 변함)	
         */
        for(int i=0; i<deque.size(); i++) {
        	deque.poll();
        }
        
        printDeque(deque);
        
        // 큐의 모든 요소를 삭제
        deque.clear();
        
        System.out.println();
        System.out.println("* push만 이용하면 stack처럼 동작[LIFO : Last-in, first-out]");
        System.out.println("---------[==> push]----------");
        
        // push 를 이용한 데이터 입력
        deque.push(5);
        printDeque(deque);
        deque.push(4);
        printDeque(deque);
        deque.push(3);
        printDeque(deque);
        deque.push(2);
        printDeque(deque);
        deque.push(1);
        printDeque(deque);
       
        // 데이터 출력
        System.out.println("");
        System.out.println(deque.poll());
        System.out.println(deque.pop());
        System.out.println(deque.poll());
        System.out.println(deque.pop());
        System.out.println(deque.poll());
       
  
	
	}

	private static void printDeque(Deque<Integer> deque) {
		// TODO Auto-generated method stub
		
		for(int i : deque)
			System.out.printf("%d ", i);
		
		System.out.println();
		
		
		
	}
}
