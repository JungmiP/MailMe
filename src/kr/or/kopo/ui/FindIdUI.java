package kr.or.kopo.ui;

import java.util.regex.Pattern;

public class FindIdUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		System.out.println("******** 아이디 찾기 ********");
		String name;
		while(true) {
			name = scanStr("> 이름을 입력하세요(종료 0): ");
			if(name.equals("0")) {
				 break;
			 } else if(!Pattern.matches("^[a-zA-Zㄱ-ㅎ가-힣]*$", name))
				System.out.println("이름에는 영문과 한글만 입력할 수 있습니다.");
			else break;
		}
		String phone;
		while(true) {
			phone = scanStr("> 핸드폰 번호를 입력하세요('-' 제외 / 종료 0): ");
			if(phone.equals("0")) {
				 break;
			 } else if(Pattern.matches("^[0-9]*$", phone) && phone.length() == 11)
				break;
			else
				System.out.println("잘못 입력하셨습니다!");		
		}
		// 아이디 찾는 메소드
	 	String id = memberService.searchMemberIdByNmPhone(name, phone);
	 	// 아이디 출력
	 	if(id != null)
	 		System.out.println(">> 아이디: " + id);
	 	else
	 		System.out.println("아이디가 존재하지 않습니다.");
	}

}
