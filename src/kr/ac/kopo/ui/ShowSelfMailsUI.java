package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.vo.SelectedMailVO;

public class ShowSelfMailsUI extends ShowMailsUI{
	
	
	public ShowSelfMailsUI() {
		super();
	}

	public ShowSelfMailsUI(List<SelectedMailVO> list) {
		super(list);
	}
	
	@Override
	public void printMails() {
		System.out.println("===========================================================");
		System.out.println("\t\t< 내게 쓴 메일함 >");
		System.out.println("===========================================================");
		System.out.println("번호\t제목\t\t\t수신일\t");
		System.out.println("-----------------------------------------------------------");
		if(list.size() == 0) {
			System.out.println("\t메일이 없습니다.");
		} else {
			for(int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "\t");
				System.out.printf("%-20s\t", list.get(i).getMailTitle());
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
