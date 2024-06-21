package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SelectedMailVO;

public class TrashUI extends MailBaseUI {
	private List<SelectedMailVO> list;
	
	public int chooseMenu() {
		System.out.println("********** 휴지통 ***********");
		System.out.println("[1] 전체 메일 조회");
		System.out.println("[2] 메일 검색");
		System.out.println("[3] 휴지통 비우기");
		System.out.println("[0] 이전 메뉴로");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		return choice;
	}
	
	@Override
	public void execute() throws Exception {
		Loop1:
		while(true) {
			int choice = chooseMenu();
			switch(choice) {
			case 1:
				showAllMails();
				break;
			case 2:
				searchMail();
				break;
			case 3:
				removeAllMail();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다. ");
				break Loop1;
			}
			
			if(list != null) {				
				ShowMailsUI show = new ShowTrashMailsUI(list);
				show.execute();
				list = null;
			}
		}
		
	}
	
	
	public void showAllMails() {
		list = mailService.searchAllTrashMails();
		
	}
	

	public void searchMail() {
		String word;
		while(true) {
			 word = scanStr("> 검색어를 입력하세요: ");
			if(word.length() > 0) {
				list = mailService.searchTrashMailByWord(word);
				break;
			} else 
				System.out.println("한 글자 이상 입력해주세요!");	
		}
	}

	public void removeAllMail() {
		String chk = scanStr("> 정말 휴지통을 비우겠습니까?(y/n): ");
		switch(chk.toLowerCase()) {
		case "y":
			mailService.deleteAllTrashMails();
			if(list != null) list.clear();
			System.out.println("휴지통 비우기를 완료했습니다.");
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
