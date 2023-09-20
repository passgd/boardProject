package com.kosa.pro7.domain;

import java.sql.Date;
import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDTO {
	
	private int noticeid;   //번호
	private String title;  //제목
	private String contents; //내용
	private String email;  //관리자
	private Date reg_date; //등록 시간
	private Date mod_date; //수정 시간
	private int view_count; //조회수
	private String fixed_yn; //고정 여부
	private String delete_yn; 	// 삭제유무
	private int N;

	private String [] ids; // 삭제시 사용될 아이디들
	
	//검색필드
	@Builder.Default
	private String searchTitle = "";
	
	//페이징
	@Builder.Default
	private int pageNo = 1;		   // 현재 페이지 번호
	private int totalCount; 	  //전체 건수
	private int totalPageSize; 	  //전체 페이지수
	@Builder.Default
	private int pageLength = 10; //한페이지의 길이
	@Builder.Default
	private int navSize = 10;	 //페이지 하단에 출력되는 페이지의 항목수
	@Builder.Default
	private int navStart = 0;    //페이지 하단에 출력되는 페이지 시작 번호 : NavStart = (PageNo / NavSize) * NavSize + 1
	@Builder.Default
	private int navEnd = 0;     //페이지 하단에 출력되는 페이지 끝 번호 : NavEnd = (PageNo / NavSize + 1) * NavSize
	
	public String getContentsHTML() {
		return contents.replace("\n", "<br/>");
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		//전체 페이지 건수를 계산
		totalPageSize = (int) Math.ceil((double) totalCount / pageLength);
		
		// 페이지 네비게이터 시작 페이지를 계산
		navStart = ((pageNo -1) / navSize) * navSize + 1;
		
		//페이지 네비게이터 끝 페이지를 계산
		navEnd = ((pageNo -1) / navSize + 1) * navSize;
		
		//전체 페이지 보다 크면 전체 페이지 값을 변경
		if(navEnd >= totalPageSize) {
			navEnd = totalPageSize;
		}
	}
	
	public int getStarNo() {
		return (pageNo -1) * pageLength + 1;
	}
	
	public int getEndNo() {
		return pageNo * pageLength;
	}
	
	
}
