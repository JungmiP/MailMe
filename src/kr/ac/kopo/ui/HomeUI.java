package kr.ac.kopo.ui;

public class HomeUI extends BaseUI{
	
	public int chooseMenu() {
		System.out.println("*****************************");
		System.out.println("\tMAIL ME HOME");
		System.out.println("*****************************");
		System.out.println("[1] 로그인");
		System.out.println("[2] 회원가입");
		System.out.println("[3] ID/PW 찾기");
		System.out.println("[0] 프로그램 종료");
		System.out.println("=============================");
		int choice = scanInt("> 메뉴 번호를 입력하세요: ");
		return choice;
	}
	
	@Override
	public void execute() throws Exception{
		IBaseUI ui = null;		
		while(true) {
			int choice = chooseMenu();
			switch(choice) {
			case 1:
				ui = new LoginUI();
				break;
			case 2:
				ui = new JoinUI();
				break;
			case 3:
				ui = new FindIdPwUI();
				break;
			case 0:
				ui = new ExitUI();
				break;
			default:
				System.out.println("잘못 선택하셨습니다.");
			}
			if (ui != null)
				ui.execute();
		}
	}
}
