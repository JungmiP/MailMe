package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.vo.MemberVO;

public class MemberService {
	
	private MemberDAO memberDao = new MemberDAO();
	
	public void createMember(MemberVO member) {
		memberDao.insertMember(member);
	}
	
	public MemberVO searchMember(String id, String password) {
		MemberVO member = memberDao.selectOneMember(id, password);
		return member;
	}
	
	public List<MemberVO> searchAllMember() {		
		List<MemberVO> list = memberDao.selectMembers();
		return list;
	}
	
	
	public int searchMemberCdByIdPw (String id, String password) {
		int memberCd = memberDao.selectMemberCdByIdPw(id, password);	
		return memberCd;
	}
	
	public int searchMemberCdById(String id) {
		int memberCd = memberDao.selectMemberCdById(id);	
		return memberCd;
	}
	
	public int searchPhoneCnt(String phone) {
		int phoneCnt = memberDao.selectPhoneCnt(phone);
		return phoneCnt;
	}
	public String searchMemberIdByNmPhone(String name, String phone) {
		String memberId = memberDao.selectMemberIdByNmPhone(name, phone);
		return memberId;
	}
	
	public int searchMemberCntByMemberCd(int memberCd) {
		int cnt = memberDao.selectMemberCdCnt(memberCd);
		return cnt;
	}
	
	public int updateMemberPw (String id, String phone, String pw) {
		int result = memberDao.updateMemberPw(id, phone, pw);
		return result;
	}

	public void updateMemberPhone (int memberCd, String phone) {
		memberDao.updateMemberPhone(memberCd, phone);
	}
	
	public void updateMemberStatus(int memberCd, String status) {
		memberDao.updateMemberStatus(memberCd, status);
	}
}
