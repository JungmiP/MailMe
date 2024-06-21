package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SpamVO;

public class SpamWordUI extends MailBaseUI{
	List<SpamVO> list;
	public int chooseMenu() {
		System.out.println("****** 스팸 단어 설정 *******");
		System.out.println("[1] 스팸 단어 등록");
		System.out.println("[2] 스팸 단어 목록");
		if(list != null)
		System.out.println("[3] 스팸 단어 삭제");
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
				String word;
				while(true) {
					 word = scanStr("> 스팸 등록할 단어를 입력하세요(종료 0): ");
					 if(word.equals("0")) break;
					 else if(word.length() > 0) {
						 mailService.registerSpamWord(word);
						 System.out.println("단어 등록 완료");
						 break;
					 }
					 else {
						 System.out.println("한 글자 이상 입력해주세요! ");
					 }
				}
				break;
			case 2: 
				showSpamWords();					
				break;
			case 3:
				removeSpamWord();
				break;
			case 0: 
				System.out.println("이전 메뉴로 돌아갑니다.");
				break Loop1;
			default: 
				System.out.println("잘못 입력하셨습니다.");
			}			
				
		}
		
	}
	
	
	public void showSpamWords(){
		List<SpamVO> list = mailService.searchAllSpamWords();
		System.out.println("========================");
		System.out.println("번호\t 단어");
		System.out.println("========================");
		for(int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + "\t" + list.get(i).getSpamWord() );
		}
	}
	
	public void removeSpamWord() {
		int no = scanInt("> 삭제할 단어의 번호를 입력하세요: ");
		if(no > 0 && no <= list.size()) {
			mailService.removeSpamWord(no - 1);
			list.remove(no - 1);
			System.out.println("삭제 완료했습니다. ");
		}else {
			System.out.println("해당 번호의 단어가 없습니다. ");
		}
			
	}
	
}
