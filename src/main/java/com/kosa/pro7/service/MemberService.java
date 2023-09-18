package com.kosa.pro7.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.pro7.domain.MemberDTO;
import com.kosa.pro7.mappers.MemberDAO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	

	//회원가입
	public boolean insert(MemberDTO member) throws Exception{
	    return memberDAO.insert(member);
	}
	
	//중복아이디검사
	public boolean checkEmail(MemberDTO member)throws Exception{
	    int count = memberDAO.checkEmail(member);

	    return count > 0;
	}
	
	//로그인
	public MemberDTO login(MemberDTO member, HttpSession session)throws Exception{
		System.out.println("MemberService.login()");
		return memberDAO.login(member);
	}
	
	//아이디 찾기
	public MemberDTO findId(MemberDTO member)throws Exception {
		System.out.println("MemberService.findId()");
		return memberDAO.findId(member);
	}
	
	//비밀번호 찾기
	public MemberDTO findPwd(MemberDTO member) throws Exception{
		System.out.println("MemberService.findPwd()");
		return memberDAO.findPwd(member);
	}
	
	//회원정보보기
	public MemberDTO view(MemberDTO memeber) throws Exception{
		System.out.println("MemberService.view()");
		return memberDAO.view(memeber);
	}
	
	//회원정보수정
	public MemberDTO update(MemberDTO member)throws Exception {
		System.out.println("MemberService.update()");
		
		if(memberDAO.update(member) == 1) {
			return null;
		}else {
			return member;
	  }
	}
	
	//회원탈퇴
	public boolean delete(MemberDTO member) throws Exception{
		System.out.println("MemberService.delete()");
		
		int count = memberDAO.delete(member);
		System.out.println("count 확인: " +count);
		return count != 0;
	}
	
//	//회원 페이지 목록 조회
//	public Map<String, Object>memberPageList(MemberDTO member)throws Exception{
//		//1.전체 건수 얻기
//		member.setTotalCount(memberDAO.totalCount(member));
//		
//		Map<String, Object> result = new HashMap<>();
//		
//		result.put("member", member);
//		result.put("list", memberDAO.getMemberList(member));
//		
//		return result;
//	}
//	
//	//회원 체크 삭제
//	public boolean memberDeletes(MemberDTO member)throws Exception{
//		return 0!= memberDAO.memberDeletes(member.getIds());
//	}
//	
//	public List<MemberDTO> getMemberList(MemberDTO member)throws Exception{
//		return memberDAO.getMemberListBoforeN(member,member.getIds().length);
//	}
//	
//	public static void main(String[] args)throws Exception {
//		MemberService memberService = new MemberService();
//		memberService.memberDAO = new MemberDAOImpl();
//		MemberDTO member = new MemberDTO();
//		member.setPageNo(11);
//		
//		Map<String, Object> result = memberService.memberPageList(member);
//		
//		System.out.println(result);
//	}
}
