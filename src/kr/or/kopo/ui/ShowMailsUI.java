package kr.or.kopo.ui;

import java.util.List;

import kr.or.kopo.vo.SelectedMailVO;

public abstract class ShowMailsUI extends MailBaseUI implements IMailboxUI{
	protected List<SelectedMailVO> list;
	
	
	public ShowMailsUI() {
		super();
	}


	public ShowMailsUI(List<SelectedMailVO> list) {
		this.list = list;
	}
	
		
	@Override
	public void removeMail() {
		// 휴지통으로 이동
		int no = scanInt("> 삭제하고 싶은 메일 번호를 선택하세요: ");
		if(no > 0 && no <= list.size()) {
			mailService.moveMail(list.get(no - 1).getMailCd());	
			list.remove(no - 1);
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("해당하는 메일이 없습니다.");
		}
	}
	

	@Override
	public void execute() throws Exception {
		Loop1 :
		while(true) {			
			printMails();
			int choice = chooseMenu();
			switch(choice){
			case 1:
				int no = scanInt("> 읽고 싶은 메일 번호를 입력하세요: ");
				if(no > 0 && no <= list.size()) {					
					SelectedMailVO mail = list.get(no - 1);
					ShowOneMailUI oneMail = new ShowOneMailUI(mail);
					oneMail.execute();
				} else {
					System.out.println("해당 번호의 메일이 없습니다.");
				}
				break;
			case 2:
				removeMail();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				break Loop1;
			}
		}
		
	}


	
}
