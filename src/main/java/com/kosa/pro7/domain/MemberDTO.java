package com.kosa.pro7.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDTO {
	
	private String email;        //아이디
	private String pwd; 	     //비밀번호
	private String name;	     //이름
	private String birthday;     //생일
	private String phone;	    //전화번호
	private String gender;	    //성별
	private String address;	    //주소
	private String role;       //admin 역할
	private String [] ids; 	   //삭제시 사용될 아이디
	private String N;
	private int outdelete;
	
	public boolean isEqualPwd(MemberDTO member) {
		return pwd.equals(member.getPwd());
	}

	
	public String getRole() {
		return role;
	}

	//검색필드
	private String searchTitle = "";

	//페이징
		private int pageNo = 1;		   // 현재 페이지 번호
		private int totalCount; 	  //전체 건수
		private int totalPageSize; 	  //전체 페이지수
		private int pageLength = 10; //한페이지의 길이
		private int navSize = 10;	 //페이지 하단에 출력되는 페이지의 항목수
		private int navStart = 0;    //페이지 하단에 출력되는 페이지 시작 번호 : NavStart = (PageNo / NavSize) * NavSize + 1
		private int navEnd = 0;     //페이지 하단에 출력되는 페이지 끝 번호 : NavEnd = (PageNo / NavSize + 1) * NavSize
		
		
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
		
		public String getEmail(){
			return email;
		}
		
		public String getPwd(){
			return pwd;
		}
		
	}
