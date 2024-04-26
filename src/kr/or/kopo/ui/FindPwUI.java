package kr.or.kopo.ui;

import java.util.regex.Pattern;

public class FindPwUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		System.out.println("******** 비밀번호 찾기 ********");
		String id;
		while(true) {			
			id = scanStr("> 아이디를 입력하세요(종료 0): ");
			// 중복 체크
			if(memberService.searchMemberCdById(id) == 0) {
				System.out.println("존재하지 않는 아이디입니다.");
			} else if(id.equals("0")) {
				 break;
			} else if(!Pattern.matches("^[a-zA-Z0-9]*$", id)) {
				System.out.println("아이디에는 영어와 숫자만 사용 가능합니다.");
			} else break;
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
		// 비밀번호 재설정
		String password;
		while(true) {
			password = scanStr("> 변경할 비밀번호를 입력하세요(6자 이상 / 종료 0): ");
			if(password.equals("0")) {
				 break;
			}else if(password.length() >= 6)
				break;
			else
				System.out.println("비밀번호가 너무 짧습니다.");
		}
		
		int result = memberService.updateMemberPw(id, phone, password);
		if(result == 1) {			
			System.out.println("비밀번호 변경이 완료되었습니다.");
		}else {
			System.out.println("비밀번호 변경 실패하였습니다.");
		}
	}

}
