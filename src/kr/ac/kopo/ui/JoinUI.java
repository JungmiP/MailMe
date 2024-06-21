package kr.ac.kopo.ui;

import java.util.regex.Pattern;

import kr.ac.kopo.vo.MemberVO;

public class JoinUI extends BaseUI{
	
	@Override
	public void execute() throws Exception {
		System.out.println("******** 회원가입 ********");
		String id;
		while(true) {			
			id = scanStr("> 아이디를 입력하세요(종료 0): ");
			// 중복 체크
			if(memberService.searchMemberCdById(id) != 0) {
				System.out.println("사용할 수 없는 아이디입니다.");
			}else if(!Pattern.matches("^[a-zA-Z0-9]*$", id)) {
				System.out.println("아이디에는 영어와 숫자만 사용 가능합니다.");
			} 
			else if(id.equals("0")) {
				 break;
			} else break;
		}
		String password;
		while(true) {
			password = scanStr("> 비밀번호를 입력하세요(6자 이상 / 종료 0): ");
			if(password.length() >= 6)
				break;
			else if(password.equals("0")) {
				 break;
			} else
				System.out.println("비밀번호가 너무 짧습니다.");
		}
		
		String name;
		while(true) {
			name = scanStr("> 이름을 입력하세요(종료 0): ");
			if(name.equals("0")) {
				 break;
			} else if(!Pattern.matches("^[a-zA-Zㄱ-ㅎ가-힣]*$", name))
				System.out.println("이름에는 영문과 한글만 입력할 수 있습니다.");
			else 
				break;
		}
		
		String birthDate;
		while(true) {
			birthDate = scanStr("> 생년월일 8자리를 입력하세요(종료 0): ");
			if(birthDate.equals("0")) {
				 break;
			} else if(Pattern.matches("(19|20)\\d{2}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])", birthDate))
				break;
			else
				System.out.println("잘못 입력하셨습니다!");
		}
		// 길이 체크
		String phone;
		while(true) {
			phone = scanStr("> 핸드폰 번호를 입력하세요('-' 제외 / 종료 0): ");
			int phoneCnt = memberService.searchPhoneCnt(phone);
			if(phoneCnt >= 1) {
				System.out.println("이미 사용중인 핸드폰 번호입니다. ");
			} else if(phone.equals("0")) {
				 break;
			} else if(Pattern.matches("^[0-9]*$", phone) && phone.length() == 11)
				break;
			else
				System.out.println("잘못 입력하셨습니다!");		
		}
		
		MemberVO member = new MemberVO(id, password, name, birthDate, phone);
		
		memberService.createMember(member);
		
		System.out.println("가입이 완료되었습니다. ");
		
	}

}
