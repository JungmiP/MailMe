package kr.ac.kopo.ui;

import java.util.Scanner;
import java.util.regex.Pattern;

import kr.ac.kopo.service.MemberService;
import kr.ac.kopo.service.MemberServiceFactory;


public abstract class BaseUI implements IBaseUI{
	private Scanner sc;
	protected MemberService memberService;
	
	BaseUI () {
		sc = new Scanner(System.in);
		memberService = MemberServiceFactory.getInstance();
	}
	
	public String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	public int scanInt(String msg) {
		String str;		
		int cnt = 0;
		while(true) {
			str = scanStr(msg);
			if (Pattern.matches("^[0-9]*$", str)) {
				break;
			}
			else {
				System.out.println("숫자만 입력해주세요!");
			}
			cnt++;
		}
		
		if(cnt == 10) {
			System.out.println("잦은 오류 발생으로 프로그램을 강제 종료합니다.");
			System.exit(0);
		}			
		return Integer.parseInt(str);
	}
}
