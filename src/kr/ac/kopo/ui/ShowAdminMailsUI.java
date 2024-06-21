package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SelectedMailVO;

public class ShowAdminMailsUI extends ShowMailsUI{
	
	
	public ShowAdminMailsUI() {
		super();
	}

	public ShowAdminMailsUI(List<SelectedMailVO> list) {
		super(list);
	}
	
	@Override
	public void printMails() {
		System.out.println("===========================================================");
		System.out.println("\t\t    < 문의 메일함 >");
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t\t\t보낸 사람   수신일\t");
		System.out.println("-----------------------------------------------------------");
		if(list.size() == 0) {
			System.out.println("\t    메일이 없습니다.");
		} else {
			for(int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "\t");
				System.out.printf("%-15s \t", list.get(i).getMailTitle());
				if(list.get(i).getMailSenderId().equals("admin")) {
					System.out.printf("%-11s", "관리자");
				} else {				
					System.out.printf("%-11s", list.get(i).getMailSenderId());
				}
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
