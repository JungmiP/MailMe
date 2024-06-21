package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SelectedMailVO;

public class AdminInboxUI extends MailBaseUI{
	private List<SelectedMailVO> list;
	
	public int chooseMenu() {
		System.out.println("******** 문의 메일함 ********");
		System.out.println("[1] 전체 메일 조회");		
		System.out.println("[2] 안읽은 메일 조회");
		System.out.println("[3] 메일 검색");
		System.out.println("[4] 전체 삭제");		
		System.out.println("[0] 이전 메뉴로");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		return choice;
	}
	
	@Override
	public void execute() throws Exception {				
		Loop1 :
		while(true) {
			int choice = chooseMenu();
			switch(choice) {
			case 1:
				showAllMails();			
				break;
			case 2: 
				showUnreadMails();
				break;
			case 3: 				
				searchMail();
				break;
			case 4:
				removeAllMails();
				break;
			case 0: 
				System.out.println("이전 메뉴로 돌아갑니다. ");
				break Loop1;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}			
			if(list != null) {				
				ShowMailsUI show = new ShowAdminMailsUI(list);
				show.execute();
				list = null;
			}
		
		}
	}
	
		
	public void showAllMails() {		
		list = mailService.searchAdminMails();
		
	}
	
	public void showUnreadMails() {
		list = mailService.searchUnreadAdminMails();
	}
	
	
	public void searchMail() {
		String word;
		while(true) {
			 word = scanStr("> 검색어를 입력하세요(종료 0): ");
			 if(word.length() > 0) {
				 list = mailService.searchAdminMailsByWord(word);		
				 break;
			 }else if(word.equals("0")) {
				 break;
			 }
			 else {
				 System.out.println("한 글자 이상 입력해주세요! ");
			 }
		}
		
	}

	public void removeAllMails() {
		
		String chk = scanStr("> 정말 전부 삭제하시겠습니까?(y/n): ");
		switch(chk.toLowerCase()) {
		case "y":
			mailService.deleteAllAdminMails();
			list.clear();
			System.out.println("전부 삭제했습니다.");
			break;
		case "n":
			System.out.println("삭제를 취소합니다. ");
			return;
		default:
			System.out.println("잘못 입력하셨습니다!");
		}	
		showAllMails();
	}
}
