package com.kosa.pro7.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.pro7.domain.MemberDTO;
import com.kosa.pro7.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	//회원가입
	@PostMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody MemberDTO member) throws Exception {		
		
		System.out.println("회원가입" + member);
		Map<String, Object> result = new HashMap<>();
		
		if(memberService.insert(member)) {
			result.put("status", true);
			result.put("message", "회원가입이 실패했습니다.");
		}else {
			result.put("status", false);
			result.put("message", "회원가입이 성공했습니다.");
		}
		return result;
	}
	
	//중복 아이디 검사
	@PostMapping("/checkEmail")
	@ResponseBody
	public Map<String, Object> checkEmail(@RequestBody MemberDTO member) throws Exception {
		System.out.println("중복확인" + member);
		System.out.println("중복확인" + memberService);
		
		Map<String, Object> result = new HashMap<>();
		 
		if(memberService.checkEmail(member)) {
			result.put("message", "아이디가 사용 불가능 합니다");
		}else {
			result.put("message", "아이디가 사용가능합니다");
		}
		return result;
	}

	
	//로그인
	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody MemberDTO member, HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		
		Map<String, Object> result = new HashMap<>();
		
		System.out.println("result 값 확인: " + result);
		
		MemberDTO loginResult = memberService.login(member, session);
		
		System.out.println("로그인 확인: " + loginResult);
		if(loginResult != null && loginResult.isEqualPwd(member)) {
			session.setAttribute("loginMember", loginResult);
			result.put("loginResult", loginResult);
			result.put("message", "로그인 성공했습니다.");
		}else {
			result.put("message", "로그인 실패했습니다.");
		}
		return result;
	}
	
	//로그아웃
	@GetMapping("/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		System.out.println("logout()");
		
		Map<String, Object> result = new HashMap<>();
		
		session.invalidate();
		
		result.put("message", "로그아웃 되었습니다.");
		
		return result;
	}
	
	//아이디 찾기
	@PostMapping("/findId")
	@ResponseBody
	public Map<String, Object> findId(@RequestBody MemberDTO member) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		
		MemberDTO findID = memberService.findId(member);
		
		if(findID != null) {
			String email = findID.getEmail();
			result.put("message", "아이디를 찾았습니다. \n 회원님의 아이디는: " + email + " 입니다.");
		}else {
			result.put("message", "이름 또는 전화번호가 잘못 입력했습니다.");
		}
		return result;
	}

	//비밀번호 찾기
	@PostMapping("/findPwd")
	@ResponseBody
	public Map<String, Object> findPwd(@RequestBody MemberDTO member) throws Exception {

		Map<String, Object> result = new HashMap<>();
		
		MemberDTO findPwd = memberService.findPwd(member);
		
		if(findPwd != null) {
			String pwd = findPwd.getPwd();
			result.put("message", "비밀번호를 찾았습니다. \n 회원님의 비밀번호는: " + pwd + " 입니다.");
		}else {
			result.put("message", "이메일 또는 전화번호가 잘못 입력했습니다.");
		}
		return result;
	}
	

	//상세정보
	@GetMapping("/view")
	@ResponseBody
	public Map<String, Object> view(HttpSession session) throws Exception {
		
		Map<String, Object> result = new HashMap<>();
		
		MemberDTO loginMember = (MemberDTO)session.getAttribute("loginMember");
		
		if(loginMember != null) {
			
			result.put("memberInfo", memberService.view(loginMember));
		}
		return result;
	}
	
	//회원정보 수정
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> update(@RequestBody MemberDTO member, HttpSession session) throws Exception {
		System.out.println("update()");
	
		Map<String, Object> result = new HashMap<>();
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		if(loginMember != null) {
						
			if(member.getEmail() != null && !member.getEmail().trim().isEmpty()) {
				loginMember.setEmail(member.getEmail());
			}
			if(member.getPwd() != null && !member.getPwd().trim().isEmpty()) {
				loginMember.setPwd(member.getPwd());
			}
			if(member.getPhone() != null && !member.getPhone().trim().isEmpty()) {
				loginMember.setPhone(member.getPhone());
			}
			if(member.getAddress() != null && !member.getAddress().trim().isEmpty()) {
				loginMember.setAddress(member.getAddress());
			}
			
			MemberDTO updatedMember = memberService.update(loginMember);
			
			System.out.println("updatedMember : " + updatedMember);
			
			if(updatedMember != null) {
			result.put("message", "회원 정보가 성공적으로 변경되었습니다.");
			result.put("memberUpdate", updatedMember);
		}else {
			result.put("message", "회원 정보 변경에 실패했습니다.");
			}
		}
		return result;
	}
	
	//회원 탈퇴
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody MemberDTO member, HttpSession session) throws Exception {
		System.out.println("delete()");
		
		Map<String, Object> result = new HashMap<>();
		
		MemberDTO loginMember = (MemberDTO) session.getAttribute("loginMember");
		
		if(loginMember !=  null && loginMember.isEqualPwd(member)) {
			
			session.invalidate();
			
			boolean delete = memberService.delete(loginMember);
			
			System.out.println("삭제 확인: " + delete);
			if(delete) {
				result.put("message", "정삭적으로 회원 탈퇴가 되었습니다.");
				result.put("success", true);
				result.put("loginMember", loginMember);
			}else {
				result.put("message", "회원 탈퇴 처리 중 오류가 발생했습니다.");
				result.put("success", false);
			}
		}
		return result;
	}
	
//	//회원 전체 목록 보기
//	public String list(MemberDTO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("member.controller.MemberList() ");
//
//		
//		try {
//			request.setAttribute("result", memberService.memberPageList(member));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "member/list.jsp";
//	}
//	@Override
//	public String toString() {
//		return "MemberController [memberService=" + memberService + "]";
//	}
//	
//	//체크박스 삭제
//	public String deletes(MemberDTO member, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("member.controller.MemberDeles()");
//		JSONObject jsonResult = new JSONObject();
//		boolean status = memberService.memberDeletes(member);
//		jsonResult.put("status", status);
//    	jsonResult.put("message", status ? "회원이 삭제 되었습니다" : "회원 삭제시 오류가 발생하였습니다");
//		
//		if (status) {
//        	jsonResult.put("memberList", memberService.getMemberList(member));
//    	}
//
//    	return jsonResult.toString();
//	}
}
