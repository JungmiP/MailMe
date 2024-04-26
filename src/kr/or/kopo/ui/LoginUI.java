package kr.or.kopo.ui;

import kr.or.kopo.vo.MemberVO;

public class LoginUI extends BaseUI{

	@Override
	public void execute() throws Exception {		
		IBaseUI ui = null;
		int cnt = 1;
		Loop1: 
		while(true) {
			System.out.println("*********** 로그인 **********");
			String id = scanStr("> ID: ");
			String password = scanStr("> PASSWORD: ");
			// 존재 , 일치여부 확인

			MemberVO member = memberService.searchMember(id, password);
			
			if(member != null) {
				if(member.getMemberStatus().equals("D")) {
					System.out.println("탈퇴한 회원입니다.");
				}
				else if(member.getMemberStatus().equals("R")) {
					System.out.println("활동 정지된 회원입니다.");
				}
				else if (member.getMemberCd() == 1)
					ui = new AdminUI();
				else
					ui = new MailMenuUI(member);
				break;
			} else if(cnt == 5) {
				// 비밀 번호 5회 틀렸을 때 처리
				System.out.println("아이디/비밀번호를 5번 틀렸습니다! ");
				String choice = scanStr("> 아이디/비밀번호를 찾으시겠습니까?(y/n): ");
				// ID/PW 찾기 UI 호출 
				switch(choice.toLowerCase()) {
				case "y":
					ui = new FindIdPwUI();
					ui.execute();
					break;
				case "n":
					System.out.println("이전 메뉴로 돌아갑니다.");
					break Loop1;
				default :
					System.out.println("잘못 입력하셨습니다.");
					break;
				}
				cnt = 1;
			} else {
				System.out.println("아이디 혹은 비밀번호를 "+ (cnt++) + "회 틀렸습니다.");
				
			}			
		}
		if(ui != null) ui.execute();
	}

}
