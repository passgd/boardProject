package com.kosa.pro7.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosa.pro7.domain.NoticeDTO;

@Mapper
public interface NoticeDAO {
	
	//공지사항 상세보기
	public NoticeDTO noticeView(int noticeid)throws Exception;
	
	//공지사항 등록
	public int noticeInsert(NoticeDTO notice)throws Exception;
	
	//공지사항 수정
	public int noticeUpdate(NoticeDTO notice)throws Exception;
	
	//공지사항 삭제
	public int noticeDelete(int noticeid)throws Exception;
	
	//공지사항 전체삭제
//	public int noticeDeletes(Map<String, Object> params)throws Exception;
	
	//조회수
	public int noticeCount(int noticeid)throws Exception;
	
	//공지사항 TOP5
	public List<NoticeDTO> noticeTop(Map<String, Object> result)throws Exception;
	
	//검색된 전체 건수 얻는다
	public int totalCount(NoticeDTO notice)throws Exception;
	
	//페이징
	public List<NoticeDTO> noticeList(Map<String, Object> result)throws Exception;
	
	//10. 게시글 삭제 후 다시 10건으로 만들어주는 메서드
//	public List<NoticeDTO> getNoticeListBoforeN(NoticeDTO notice) throws Exception ;

}
