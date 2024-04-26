package kr.or.kopo.ui;

import java.util.List;
import java.util.regex.Pattern;

import kr.or.kopo.vo.MemberVO;

public class AdminUI extends BaseUI{
	public int chooseMenu() {
		System.out.println("*****************************");
		System.out.println("\tADMIN HOME");
		System.out.println("*****************************");
		System.out.println("[1] 회원 목록 보기");
		System.out.println("[2] 모든 회원에게 메일 보내기");
		System.out.println("[3] 문의 메일 목록 보기");
		// 사용자가 신고한 메일 내용을 보고 보낸 사람을 차단시키는 기능 구현!
		//System.out.println("[4] 스팸 메일 목록 보기");
		System.out.println("[0] 프로그램 종료");
		System.out.println("=============================");
		int choice = scanInt("> 번호를 입력하세요: ");
		return choice;
	}

	@Override
	public void execute() throws Exception {
		Loop1 :
		while(true) {
			IBaseUI ui = null;
			int choice = chooseMenu();
			switch(choice) {
			case 1:
				showAllMembers();
				break;
			case 2:
				ui = new WriteMailToAllMemberUI();
				break;
			case 3:
				ui = new AdminInboxUI();
				break;
			case 0:
				System.out.println("이전 메뉴로 돌아갑니다.");
				break Loop1;
			default:
				System.out.println("잘못 선택하셨습니다.");
				break;
			}
			if(ui != null) {
				ui.execute();
			}
		}
		
	}
	
	
	public void showAllMembers() {
		System.out.println("====================================================================================");
		System.out.println("\t\t          회원 목록");
		System.out.println("====================================================================================");
		System.out.println("번호  아이디    이름    생년월일      핸드폰번호    활동 상태   가입일\t");
		System.out.println("------------------------------------------------------------------------------------");
		List<MemberVO> list = memberService.searchAllMember();
		for(MemberVO member: list ) {
			System.out.printf("%-2d    ",member.getMemberCd());
			System.out.printf("%-10s", member.getMemberId());
			System.out.printf("%-5s", member.getMemberNm());
			System.out.printf("%-15s", member.getMemberBirthDate().substring(0,10));
			System.out.printf("%-15s", member.getMemberPhone());
			System.out.printf("%-7s", member.getMemberStatus());
			System.out.printf(member.getMemberRegDate());
			System.out.println();
			
		}
		
		System.out.println("====================================================================================");
		Loop1 :
		while(true) {			
			System.out.println("[1] 회원 상태 수정");
			System.out.println("[0] 이전 메뉴로");
			int no = scanInt("> 메뉴를 선택하세요: ");
			switch(no) {
			case 1: 
				System.out.println("S:관리자, A:활동, R:활동 중지, D:탈퇴  ");
				int memberCd = scanInt("> 변경할 회원 번호를 입력하세요: ");
				if(memberService.searchMemberCntByMemberCd(memberCd) == 1) {
					String status = scanStr("> 변경할 상태를 입력하세요: ");
					if(status.length() == 1 && Pattern.matches(status, "[SARD]")) {					
						memberService.updateMemberStatus(memberCd, status);
					}else
						System.out.println("잘못 입력하셨습니다. ");					
				}else {
					System.out.println("해당 번호의 회원이 없습니다! ");
				}				
				break;
			case 0:
				break Loop1;
			default:
				System.out.println("잘못 선택하셨습니다.");
			}		
		}
		
	}
	
	
}
