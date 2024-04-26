package kr.or.kopo.ui;

import kr.or.kopo.vo.SelectedMailVO;

public class ShowOneMailUI extends MailBaseUI{
	private SelectedMailVO mail;

	public ShowOneMailUI() {
		super();
	}

	public ShowOneMailUI(SelectedMailVO mail) {
		this.mail = mail;
	}
	
	@Override
	public void execute() {
		printMail();
		mailService.checkOpen(mail.getMailCd());
		Loop1: 
		while(true) {
			int choice = chooseMenu();
			switch(choice) {
			case 1: 
				System.out.println("받은 메일함으로 돌아갑니다.");
				break Loop1;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
		}
	}
	
	// 아직 안만들었음!!!!!!!
	public int chooseMenu() {
		System.out.println("[1] 뒤로가기   ");
		//System.out.print("[2] 답장하기   ");
		//System.out.print("[3] 삭제하기   ");
		//System.out.print("[4] 스팸신고");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		return choice;
	}
	
	public void printMail() {
		System.out.println("============================================");
		System.out.println("[ 제   목 ] " + mail.getMailTitle());
		System.out.print("[보낸 사람] ");
		if(mail.getMailSenderId().equals(MailMenuUI.loginMember.getMemberId())) {
			System.out.println("나");
		}
		else {
			System.out.println(mail.getMailSenderId());
		}
		System.out.print("[받은 사람] ");
		if(mail.getMailReceiverId().equals(MailMenuUI.loginMember.getMemberId())) {
			System.out.println("나");
		}
		else {
			System.out.println(mail.getMailSenderId());
		}
		System.out.println("[ 내   용 ]");
		System.out.println(mail.getMailContent());		
		System.out.println("============================================");
		
	}
	
}
