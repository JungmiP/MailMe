package kr.ac.kopo.service;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.dao.MailDAO;
import kr.ac.kopo.dao.MemberDAO;
import kr.ac.kopo.dao.SpamDAO;
import kr.ac.kopo.dao.TrashDAO;
import kr.ac.kopo.vo.MailVO;
import kr.ac.kopo.vo.SelectedMailVO;
import kr.ac.kopo.vo.SpamVO;

public class MailService {
	private MailDAO mailDAO = new MailDAO();
	private MemberDAO memberDAO = new MemberDAO();
	private TrashDAO trashDAO = new TrashDAO();
	private SpamDAO spamDAO = new SpamDAO();
	
	// 새로운 메일 쓰기
	public boolean writeMail(String mailTitle, String mailContent, int senderCd, String receiverId) {
		int receiverCd = memberDAO.selectMemberCdById(receiverId);
		if(receiverCd != 0) {			
			MailVO mail = new MailVO(mailTitle, mailContent, senderCd, receiverCd);
			mailDAO.insertMail(mail);		
		}else {
			return false;
		}
		return true;
	}
	
	// 메일 하나 조회
	public SelectedMailVO searchMail(int mailCd) {
		//mailDAO.selectMail();
		
		return null;
	}
	
	// 모든 메일 조회 (받은, 보낸 메일함)
	public List<SelectedMailVO> searchAllMails(String mode) {
		List<SelectedMailVO> list = null;
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str = spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}		
			list = mailDAO.selectAllMails(mode, str);
		}else {
			list = mailDAO.selectAllMails(mode);
		}
		return list;
	}
	
	// 메일 검색(받은, 보낸 메일함)
	public List<SelectedMailVO> searchMailsByWord(String mode, String word){
		List<SelectedMailVO> list = null;
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str = spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}			
			list = mailDAO.selectMailsByWord(mode, word, str);
		}else {
			list = mailDAO.selectMailsByWord(mode, word);
		}
		
		return list;
	}
	
	// 휴지통으로 메일 하나 이동
	public void moveMail(int mailCd) {
		trashDAO.insertTrash(mailCd);
	}

	// 수신 확인 업데이트
	public void checkOpen(int mailCd) {
		mailDAO.updateOpenChk(mailCd);
		
	}
	
	// 휴지통 메일 전체 조회
	public List<SelectedMailVO> searchAllTrashMails() {
		List<SelectedMailVO> list = mailDAO.selectAllTrashMails();
		return list;
	}

	// 휴지통 메일 하나 영구 삭제
	public void deletePermanent(int trashCd) {
		trashDAO.updatePermanent(trashCd);
		
	}
	
	// 휴지통 메일 검색
	public List<SelectedMailVO> searchTrashMailByWord(String word) {
		List<SelectedMailVO> list = mailDAO.selectTrashMailsByWord(word);
		return list;
	}
	
	// 휴지통 메일 하나 복구
	public void restoreTrashMail(int trashCd) {
		trashDAO.deleteTrash(trashCd);		
	}
	
	// 내게 쓴 메일함 전체 조회
	public List<SelectedMailVO> searchAllSelfMails() {
		List<SelectedMailVO> list = mailDAO.selectAllSelfMails();
		return list;
	}
	
	// 내게 쓴 메일함 검색
	public List<SelectedMailVO> searchSelfMailByWord(String word) {
		List<SelectedMailVO> list = mailDAO.selectSelfMailsByWord(word);
		return list;
	}
	
	
	// 안읽은 메일 전체 조회
	public List<SelectedMailVO> searchUnreadMails(String mode) {
		List<SelectedMailVO> list;
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str = spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}		
			list = mailDAO.selectUnreadMails(mode, str);
		}else {
			list = mailDAO.selectUnreadMails(mode);
		}
		return list;
	}
	
	// 스팸 단어 설정
	public void registerSpamWord(String word) {
		spamDAO.insertSpam(word);
	}
	
	// 스팸 단어 조회
	public List<SpamVO> searchAllSpamWords(){
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		return spamWords;		
	}
	
	// 스팸메일 모두 조회
	public List<SelectedMailVO> searchSpamMails() {	
		List<SelectedMailVO> list = new ArrayList<>();
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str = spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}
			list = mailDAO.selectSpamMails(str);
		}
		return list;
	}
	
	// 스팸 메일 검색
	public List<SelectedMailVO> searchSpamMailsByWord(String word) {
		List<SelectedMailVO> list = null;
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str =  spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}
		list = mailDAO.selectSpamMailsByWord(str, word);
		}
		return list;
	}
	
	
	//전체 삭제
	public void deleteAllMails(String mode) {
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str =  spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}
			trashDAO.insertMailsToTrash(mode, str);
		}else {
			trashDAO.insertMailsToTrash(mode);
		}
	}
	
	// 휴지통 비우기
	public void deleteAllTrashMails() {
		trashDAO.updateAllPermanent();
	}
	
	// 스팸 단어 삭제
	public void removeSpamWord(int spamCd) {
		spamDAO.deleteSpam(spamCd);		
	}
	
	// 스팸 메일 전체 삭제
	public void deleteAllSpamMails() {
		List<SpamVO> spamWords = spamDAO.selectAllSpamWord();
		if(spamWords.size() > 0) {
			String str =  spamWords.get(0).getSpamWord();
			for(int i = 1; i < spamWords.size(); i++) {
				str += "|";
				str += spamWords.get(i).getSpamWord();
			}		
			trashDAO.insertSpamMailsToTrash(str);
		}
	}
	
	// 모두에게 메일 쓰기
	public void writeMailToAllMembers(String title, String content) {
		MailVO mail = new MailVO();
		mail.setMailTitle(title);
		mail.setMailContent(content);
		mailDAO.insertMailToAllMembers(mail);
	}
	
	// 관리자 메일 조회
	public List<SelectedMailVO> searchAdminMails() {
		List<SelectedMailVO> list = mailDAO.selectAllAdminMails();
		return list;
	}
	
	// 안읽은 관리자 메일 조회
	public List<SelectedMailVO> searchUnreadAdminMails() {
		List<SelectedMailVO> list = mailDAO.selectUnreadAdminMails();
		return list;
	}
	
	// 관리자 메일 검색 조회
	public List<SelectedMailVO> searchAdminMailsByWord(String word) {
		List<SelectedMailVO> list = mailDAO.selectAdminMailsByWord(word);
		
		return list;
	}
	
	// 관리자 메일 전체 삭제
	public void deleteAllAdminMails() {
		trashDAO.insertAdminMailsToTrash();
	}
	
}
