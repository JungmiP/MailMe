package kr.or.kopo.ui;

public class WriteMailUI extends MailBaseUI{
	
	public WriteMailUI() {
		
	}	


	@Override
	public void execute() throws Exception {
		System.out.println("********* 메일 쓰기 *********");
		String receiverId = scanStr("> 받는 사람 ID: ");		
		String mailTitle;
		while(true) {
			mailTitle = scanStr("> 메일 제목: ");
			if(mailTitle.length() > 0) {
				break;
			}else {
				System.out.println("한 글자 이상 입력해주세요!");
			}
		}
		String mailContent;
		while(true) {
			mailContent = scanStr("> 메일 내용: ");
			if(mailContent.length() > 0) {
				break;
			}else {
				System.out.println("한 글자 이상 입력해주세요!");
			}
		}
		
		if (mailService.writeMail(mailTitle, mailContent, MailMenuUI.loginMember.getMemberCd(), receiverId)) {
			System.out.println("메일을 보냈습니다! ");
		} else{
			System.out.println("메일을 보내지 못했습니다!");
		};
		
	}


}
