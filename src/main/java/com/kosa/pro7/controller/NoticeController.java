package com.kosa.pro7.controller;

import java.util.HashMap;
import java.util.List;
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
	@PostMapping("/checkDelete")
	@ResponseBody
	public Map<String, Object> deletes(@RequestBody Map<String, Object> params) throws Exception {
		System.out.println("notice.controller.NoticeDeleteAll()");
		
		Map<String, Object> result = new HashMap<>();
		
		List<String> idsList = (List<String>) params.get("ids");
//		String[] idsArray = idsList.toArray(new String[idsList.size()]);
		
		//삭제된 공지사항의 noticeid 목록 얻기
		int deleteNoticeIds = service.noticeDeletes(params);

		if (deleteNoticeIds > 0) {
		    result.put("status", true);
		    result.put("message", "공지사항 삭제가 완료 되었습니다.");

		    // 마지막 삭제된 noticeid 가져오기
		    String lastDeleteNoticeId = idsList.get(idsList.size() - 1);

		 // 서비스를 통해 삭제된 공지사항 이후의 공지사항 목록을 가져옵니다.
		    int N = idsList.size();
		    NoticeDTO noticeDTO = new NoticeDTO();
		    noticeDTO.setIds(new String[]{lastDeleteNoticeId});
		    noticeDTO.setN(N);
		    noticeDTO.setNoticeid(Integer.parseInt((String) params.get("noticeid")));

		    List<NoticeDTO> pageNoticeList = service.getNoticeList(noticeDTO);
		    System.out.println(pageNoticeList);
		    result.put("noticeList", pageNoticeList);
		} else {
		    result.put("message", "공지사항 삭제 중 오류가 발생했습니다.");
		}		
    	return result;
	}
	
}
