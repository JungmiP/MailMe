package kr.or.kopo.ui;

import java.util.regex.Pattern;

public class MyPageUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		Loop1:
		while(true) {
			System.out.println("************************************");
			System.out.println("\t     MY PAGE");
			System.out.println("************************************");
			System.out.println("[회 원 번 호] " + MailMenuUI.loginMember.getMemberCd());
			System.out.println("[  I     D  ] " + MailMenuUI.loginMember.getMemberId());
			System.out.println("[비 밀 번 호] " + "********");
			System.out.println("[ 이     름 ] " + MailMenuUI.loginMember.getMemberNm());
			System.out.println("[핸드폰 번호] " + MailMenuUI.loginMember.getMemberPhone());
			System.out.println("[생 년 월 일] " + MailMenuUI.loginMember.getMemberBirthDate().substring(0, 10));
			System.out.println("[ 가 입  일 ] " + MailMenuUI.loginMember.getMemberRegDate());
			
			int choice = chooseMenu();
			switch(choice) {
			case 1:
				changePw();
				break;
			case 2:
				changePhone();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다! ");
				break Loop1;
			}
		}
	}
	
	public int chooseMenu() {		
		System.out.println("[1] 비밀번호 변경 ");
		System.out.println("[2] 전화번호 변경 ");		
		System.out.println("[0] 이전 메뉴로");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		return choice;
	}
	
	public void changePw() {
		String password;
		while(true) {
			password = scanStr("> 비밀번호를 입력하세요(6자 이상 / 종료 0): ");
			if(password.length() >= 6)
				break;
			else if(password.equals("0")) return;
			else
				System.out.println("비밀번호가 너무 짧습니다.");
		}
		if(memberService.updateMemberPw(MailMenuUI.loginMember.getMemberId(), MailMenuUI.loginMember.getMemberPhone(), password) == 1) {;
			MailMenuUI.loginMember.setMemberPw(password);
			System.out.println("비밀번호 변경을 완료했습니다.");
		} else {
			System.out.println("비밀번호 변경을 실패했습니다.");
		}
	}
	
	public void changePhone() {
		String phone;
		while(true) {
			phone = scanStr("> 변경할 핸드폰 번호를 입력하세요('-' 제외 / 종료 0): ");
			int phoneCnt = memberService.searchPhoneCnt(phone);
			if(phoneCnt >= 1) {
				System.out.println("이미 사용중인 핸드폰 번호입니다. ");
			}
			else if(Pattern.matches("^[0-9]*$", phone) && phone.length() == 11)
				break;
			else if(phone.equals("0")) return;
			else
				System.out.println("잘못 입력하셨습니다!");		
		}
		memberService.updateMemberPhone(MailMenuUI.loginMember.getMemberCd(), phone);
		MailMenuUI.loginMember.setMemberPhone(phone);
		System.out.println("핸드폰 번호 변경을 완료했습니다.");
	}

}
