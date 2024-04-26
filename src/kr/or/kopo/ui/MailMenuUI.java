package kr.or.kopo.ui;

import kr.or.kopo.vo.MemberVO;

public class MailMenuUI extends BaseUI{

	public static MemberVO loginMember = null;
	
	public MailMenuUI() {}

	public MailMenuUI(MemberVO member) {
		loginMember = member;
	}
	
	
	public int chooseMenu() {
		System.out.println("*****************************");
		System.out.println("\t   MAIL ME");
		System.out.println("*****************************");
		System.out.println("[1] 메일 쓰기");
		System.out.println("[2] 받은 메일함");
		System.out.println("[3] 보낸 메일함");
		System.out.println("[4] 휴지통");
		System.out.println("[5] 내게 쓴 메일함");
		System.out.println("[6] 스팸메일함");
		System.out.println("[7] 마이 페이지");
		System.out.println("[8] 로그아웃");
		System.out.println("=============================");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		
		return choice;
	}

	@Override
	public void execute() throws Exception {
		IBaseUI ui = null;
		Loop1:
		while(true) {			
			int choice = chooseMenu();
			
			switch(choice) {
			case 1: 
				ui = new WriteMailUI();
				break;
			case 2: 
				ui = new InboxUI();
				break;
			case 3: 
				ui = new SentMailUI();
				break;
			case 4: 
				ui = new TrashUI();
				break;
			case 5: 
				ui = new SelfInboxUI();
				break;
			case 6: 
				ui = new SpamUI();
				break;
			case 7: 
				String pw = scanStr("> 현재 비밀번호를 입력하세요: ");
				if(loginMember.getMemberPw().equals(pw)) {
					ui = new MyPageUI();
				}else {
					System.out.println("비밀번호를 틀렸습니다. 마이 페이지에 들어갈 수 없습니다.");
				}
				break;
			case 8: 
				loginMember = null;
				break Loop1;
			default:
				System.out.println("잘못 선택하셨습니다.");
				
			}
			if(ui != null) {
				ui.execute();
				ui = null;
			}
			
		}
		
		
	}

}
