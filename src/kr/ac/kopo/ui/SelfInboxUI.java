package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SelectedMailVO;

public class SelfInboxUI extends MailBaseUI{
	private List<SelectedMailVO> list;
	
	public int chooseMenu() {
		System.out.println("****** 내게 쓴 메일함 *******");
		System.out.println("[1] 전체 메일 조회");
		System.out.println("[2] 메일 검색");
		//System.out.println("[3] 전체 삭제");
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
				searchMail();
				break;
			case 0: 
				System.out.println("이전 메뉴로 돌아갑니다! ");
				break Loop1;		
			}
			if(list != null) {				
				ShowMailsUI show = new ShowSelfMailsUI(list);
				show.execute();
				list = null;
			}
		}
	}
	
	
	
	public void showAllMails() {		
		list = mailService.searchAllSelfMails();
	}
	


	public void searchMail() {
		String word;
		while(true) {
			 word = scanStr("> 검색어를 입력하세요: ");
			if(word.length() > 0) {
				list = mailService.searchSelfMailByWord(word);
				break;
			} else 
				System.out.println("한 글자 이상 입력해주세요!");	
		}		
		
	}

	

}
