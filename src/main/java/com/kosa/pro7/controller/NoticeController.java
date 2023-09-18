package com.kosa.pro7.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.pro7.domain.NoticeDTO;
import com.kosa.pro7.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	//공지사항 전체보기
	@RequestMapping("/list")
	public String list(NoticeDTO notice, Model model) throws Exception {
		System.out.println("notice.controller.NoticeList() ");		
		
		try {
			model.addAttribute("result", service.noticePageList(notice));
			System.out.println("컨트롤러 확인: " + service.noticePageList(notice));
		}catch (Exception e) {
			e.printStackTrace();
		}

		return "notice/list";
	}
	

	//공지사항 상세보기
	@PostMapping("/view")
	@ResponseBody
	public NoticeDTO view(@RequestBody NoticeDTO notice) throws Exception {
		System.out.println("notice.controller.NoticeView()");
		
		NoticeDTO view = service.noticeView(notice.getNoticeid());
		System.out.println(view);
		
		service.noticeCount(notice.getNoticeid());
		
		return view;
	}
	
	//공지사항 등록
	@PostMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody NoticeDTO notice) throws Exception {
		System.out.println("notice.controller.NoticeInsert()");

		Map<String, Object> result = new HashMap<>();
		
		if(service.noticeInsert(notice)) {
			result.put("message", "공지사항 글 등록 완료 되었습니다.");
		}else {
			result.put("message", "공시사항 등록 중 오류가 발생했습니다.");
		}
		
		return result;
	}
	
	//공지사항 수정
	@PostMapping("/update")
	@ResponseBody
	public Map<String, Object> update(@RequestBody NoticeDTO notice) throws Exception {
		System.out.println("notice.controller.NoticeUpdate()");
	
		Map<String, Object> result = new HashMap<>();
		
		boolean update = service.noticeUpdate(notice);
				
		System.out.println("수정 확인: " + update);
		if(update) {
			result.put("message", "공지사항 글이 성공적으로 수정되었습니다.");
			result.put("noticeUpdate", update);			
		}else {
			result.put("message", "공지사항 수정에 실패했습니다.");
		}
		
		return result;
	}

	//공지사항 삭제
	@PostMapping("/delete")
	@ResponseBody
	public Map<String, Object> delete(@RequestBody NoticeDTO notice) throws Exception {
		System.out.println("notice.controller.NoticeDelete()");
		
		Map<String, Object> result = new HashMap<>();
		System.out.println("notice = " + notice);
		boolean delete = service.noticeDelete(notice.getNoticeid());
		System.out.println("Controller 삭제 확인: " + delete);
		
		if(delete) {
			result.put("message", "공지사항 글 삭제가 완료 되었습니다.");
		}else {
			result.put("message", "공지사항 글 삭제중 오류가 발생했습니다.");
		}
		
		return result;
	}

	//공지사항 전체 삭제
//	public String deletes(NoticeDTO notice, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("notice.controller.NoticeDeleteAll()");
//		JSONObject jsonResult = new JSONObject();
//		boolean status = noticeService.noticeDeletes(notice);
//		
//		jsonResult.put("status", status);
//    	jsonResult.put("message", status ? "공지사항 글 삭제 되었습니다" : "공지사항 글 삭제시 오류가 발생하였습니다");
//		
//		if (status) {
//        	jsonResult.put("noticeList", noticeService.getNoticeList(notice));
//    	}
//
//    	return jsonResult.toString();
//	}
	
}
