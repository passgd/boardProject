package com.kosa.pro7.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosa.pro7.domain.NoticeDTO;
import com.kosa.pro7.mappers.NoticeDAO;

@Service
public class NoticeService {
	
	@Autowired
	private  NoticeDAO noticeDAO;
	

	//공지사항 전체보기
	public Map<String, Object> noticePageList(NoticeDTO notice)throws Exception{
		System.out.println("NoticeService.noticeList() 함수 호출됨");
		
		notice.setTotalCount(noticeDAO.totalCount(notice));
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			result.put("p_search_noticeid", notice.getSearchTitle());
			result.put("p_start_row", notice.getStarNo());
			result.put("p_end_row", notice.getEndNo());
			noticeDAO.noticeList(result);
			
			result.put("list", result.get("notice_cursor"));
			result.put("notice", notice);
		} catch (Exception e) {
			result.put("message", "서버에 오류 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//공지사항 등록
	public boolean noticeInsert(NoticeDTO notice)throws Exception {
		return 0 != noticeDAO.noticeInsert(notice);
	}
	
	//공지사항 수정
	public boolean noticeUpdate(NoticeDTO notice)throws Exception {
		System.out.println("NoticeService.update()");
		System.out.println("수정 서비스 확인: " + noticeDAO.noticeUpdate(notice));
		return 0 != noticeDAO.noticeUpdate(notice);
	}
	
	//공지사항 삭제
	@Transactional
	public boolean noticeDelete(int noticeid)throws Exception {
		System.out.println("공지사항 삭제 ");
		return 0 != noticeDAO.noticeDelete(noticeid);
	}
	
//	//공지사항 전체 삭제
//	public boolean noticeDeletes(Map<String, Object> params) throws Exception{
//		return 0 != noticeDAO.noticeDeletes(params);
//	}
	
	//공지사항 상세보기
	public NoticeDTO noticeView(int noticeid)throws Exception{
		return noticeDAO.noticeView(noticeid);
	}
	
	//공지사항 TOP5
	public List<NoticeDTO> noticeTop()throws Exception{
		
		Map<String, Object> result = new HashMap<>();
		
		noticeDAO.noticeTop(result);
		
		List<NoticeDTO> list = (List<NoticeDTO>) result.get("notice_cursor");
		
		return list;
	}
	
	//조회수
	public int noticeCount(int noticeid)throws Exception {
		System.out.println("조회수 확인()");
		return noticeDAO.noticeCount(noticeid);
	}
	
}
