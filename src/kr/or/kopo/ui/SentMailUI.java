package kr.or.kopo.ui;

import java.util.List;

import kr.or.kopo.vo.SelectedMailVO;

public class SentMailUI extends MailBaseUI{

	private List<SelectedMailVO> list;
	
	public int chooseMenu() {
		System.out.println("******** 보낸 메일함 ********");
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
				removeAllMail("SENDER");
				break;
			case 0:  
				System.out.println("이전 메뉴로 돌아갑니다. ");
				break Loop1;		
			}
			if(list != null) {				
				ShowMailsUI show = new ShowSentMailsUI(list);
				show.execute();
				list = null;
			}
		}
	}
	
	
	public void showAllMails() {		
		list = mailService.searchAllMails("SENDER");
		
	}
	
	public void showUnreadMails() {
		list = mailService.searchUnreadMails("SENDER");
	}
	
	public void searchMail() {
		String word;
		while(true) {
			 word = scanStr("> 검색어를 입력하세요: ");
			if(word.length() > 0) {
				list = mailService.searchMailsByWord("SENDER", word);
				break;
			} else 
				System.out.println("한 글자 이상 입력해주세요!");	
		}
	}

	public void removeAllMail(String mode) {
		String chk = scanStr("> 정말 전부 삭제하시겠습니까?(y/n): ");
		switch(chk.toLowerCase()) {
		case "y":
			mailService.deleteAllMails(mode);
			if(list != null) list.clear();
			System.out.println("전부 삭제했습니다.");
			break;
		case "n":
			System.out.println("삭제를 취소합니다. ");
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			return;
		}	
		showAllMails();
	}

}
