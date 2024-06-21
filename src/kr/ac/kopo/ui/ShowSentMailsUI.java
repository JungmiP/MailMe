package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SelectedMailVO;

public class ShowSentMailsUI extends ShowMailsUI{
	
	
	public ShowSentMailsUI() {
		super();
	}

	public ShowSentMailsUI(List<SelectedMailVO> list) {
		super.list = list;
	}
	
	@Override
	public void printMails() {
		System.out.println("===========================================================");
		System.out.println("\t\t< 보낸 메일함 >");
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t\t\t받은 사람  수신여부  수신일\t");
		System.out.println("-----------------------------------------------------------");
		if(list.size() == 0) {
			System.out.println("\t메일이 없습니다.");
		} else {
			for(int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "\t");
				System.out.printf("%-15s \t", list.get(i).getMailTitle());
				if(list.get(i).getMailReceiverId().equals(MailMenuUI.loginMember.getMemberId())) {
					System.out.printf("%-11s", "나");
				} else {				
					System.out.printf("%-11s", list.get(i).getMailReceiverId());
				}
				System.out.printf("%-4s  ", list.get(i).getMailOpenChk() == 1? "Y" : "N");
				System.out.print(list.get(i).getMailSentDate().substring(0, 10));
				System.out.println();
			}			
		}
		System.out.println("===========================================================");
		
	}
	
	
	@Override
	public int chooseMenu() {
		System.out.print("[1] 선택 메일 조회   ");
		System.out.print("[2] 메일 삭제   ");
		System.out.println("[0] 이전 메뉴로");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		return choice;
	}

}
