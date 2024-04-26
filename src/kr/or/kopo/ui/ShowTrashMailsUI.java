package kr.or.kopo.ui;

import java.util.List;

import kr.or.kopo.vo.SelectedMailVO;

public class ShowTrashMailsUI extends ShowMailsUI{
	
	
	public ShowTrashMailsUI() {
		super();
	}

	public ShowTrashMailsUI(List<SelectedMailVO> list) {
		super.list = list;
	}
	
	@Override
	public void printMails() {
		System.out.println("-------------------------------------------------------");
		System.out.println("\t\t< 휴지통 >");
		System.out.println("-------------------------------------------------------");
		System.out.println("번호\t제목\t\t메일함\t날짜");
		System.out.println("-------------------------------------------------------");
		if(list.size() == 0) {
			System.out.println("휴지통이 비었습니다.");
		} else {
			for(int i = 0; i < list.size(); i++) {
				System.out.print((i + 1) + "\t");
				System.out.printf("%-15s", list.get(i).getMailTitle());
				
				if(list.get(i).getMailSenderId().equals(MailMenuUI.loginMember.getMemberId())) {
					System.out.printf("%-5s \t", "보낸 메일");
				} else if(list.get(i).getMailReceiverId().equals(MailMenuUI.loginMember.getMemberId())){				
					System.out.printf("%-5s \t", "받은 메일");
				}
				
				System.out.print(list.get(i).getMailSentDate().substring(0, 10));
				System.out.println();
			}			
		}
		System.out.println("-------------------------------------------------------");
		
	}
	
	
	@Override
	public int chooseMenu() {
		System.out.print("[1] 선택 메일 조회   ");
		System.out.print("[2] 메일 영구 삭제   ");
		System.out.print("[3] 메일 복구   ");
		System.out.println("[0] 이전 메뉴로");
		int choice = scanInt("> 메뉴를 선택하세요: ");
		return choice;
	}
	
	@Override
	public void removeMail() {
		int no = scanInt("> 영구 삭제하고 싶은 메일 번호를 입력하세요: ");
		if (no > 0 && no <= list.size()) {			
			String chk = scanStr("> 정말 삭제하시겠습니까?(y/n): ");
			switch(chk) {
			case "y":
				mailService.deletePermanent(list.get(no - 1).getTrashCd());
				list.remove(no - 1);
				System.out.println("삭제를 완료하였습니다.");
				break;
			case "n":
				System.out.println("삭제를 취소합니다. ");
				return;
			default:
				System.out.println("잘못 입력하셨습니다!");
			}	
		}else {
			System.out.println("해당 번호의 메일이 없습니다! ");
		}
	}
	
	public void restoreMail() {
		int no = scanInt("> 복구하고 싶은 메일 번호를 입력하세요: ");
		if (no > 0 && no <= list.size()) {
			mailService.restoreTrashMail(list.get(no - 1).getTrashCd());
			list.remove(no - 1);
			System.out.println("복구 완료했습니다. ");
		} else {
			System.out.println("해당 번호의 메일이 없습니다! ");
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
				if (no > 0 && no <= list.size()) {
					SelectedMailVO mail = list.get(no - 1);
					ShowOneMailUI oneMail = new ShowOneMailUI(mail);
					oneMail.execute();
				} else {
					System.out.println("해당 번호의 메일이 없습니다! ");
				}
				break;
			case 2:
				removeMail();
				break;
			case 3:
				restoreMail();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				break Loop1;
			}
		}
		
	}
	
	

}
