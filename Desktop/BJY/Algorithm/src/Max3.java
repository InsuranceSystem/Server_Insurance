import java.util.Scanner;

public class Max3 {

	public static void main(String[] args) {
		//세 정수 값을 입력 받고 최댓값을 출력하라.
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int max = a;
		if(max<b) {
			max = b;
		}
		if(max<c) {
			max = c;
		}
		System.out.println("최댓값"+max+"입니다.");
        sc.close();
	}

}
