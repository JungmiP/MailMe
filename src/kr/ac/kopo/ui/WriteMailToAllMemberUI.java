package kr.ac.kopo.ui;

public class WriteMailToAllMemberUI extends MailBaseUI{
	
	public WriteMailToAllMemberUI() {
		
	}	
	
	@Override
	public void execute() throws Exception {
		System.out.println("********* 메일 쓰기 *********");
		String mailTitle;
		while(true) {
			mailTitle = scanStr("> 메일 제목(종료 0): ");
			if(mailTitle.equals("0")) return;
			else if(mailTitle.getBytes().length >= 100) {
				System.out.println("제목 최대 길이를 초과하였습니다.");
				break;
			}
			else if(mailTitle.length() > 0) {
				break;
			}else {
				System.out.println("한 글자 이상 입력해주세요!");
			}
		}
		String mailContent;
		while(true) {
			mailContent = scanStr("> 메일 내용(입력 취소 0 / 입력 종료 Q): ");
			if(mailContent.equals("0")) return;
			else if(mailContent.equals("Q")) break;
			else if(mailContent.getBytes().length >= 4000) {
				System.out.println("내용 최대 길이를 초과하였습니다.");
				break;			
			}
			else mailContent += "\n";
		}
		
		mailService.writeMailToAllMembers(mailTitle, mailContent);
		System.out.println("메일을 보냈습니다! ");		
	}

}
