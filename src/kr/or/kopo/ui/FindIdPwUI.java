package kr.or.kopo.ui;

public class FindIdPwUI extends BaseUI{

	@Override
	public void execute() throws Exception {
		System.out.println("**********************************");
		System.out.println("\t ID/PASSWORD 찾기");
		System.out.println("**********************************");
		Loop1 :
		while(true) {
			System.out.println("[1] 아이디 찾기");
			System.out.println("[2] 비밀번호 찾기");
			System.out.println("[0] 이전 메뉴로");
			int choice = scanInt("> 메뉴를 선택하세요: ");
			
			IBaseUI ui = null;
			switch(choice) {
			case 1:
				ui = new FindIdUI();
				break;
			case 2:
				ui = new FindPwUI();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				break Loop1;
			default:
				System.out.println("잘못 입력하셨습니다.");
			}
			if(ui != null)
				ui.execute();
		}
	}

}
