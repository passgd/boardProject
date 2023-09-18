package com.kosa.pro7.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosa.pro7.domain.MemberDTO;

@Mapper
public interface MemberDAO {
	
	//회원가입
	public boolean insert(MemberDTO member) throws Exception;
	//중복아이디검사
	public int checkEmail(MemberDTO member) throws Exception;
	//로그인
	public MemberDTO login(MemberDTO member) throws Exception;
	//아이디 찾기
	public MemberDTO findId(MemberDTO member) throws Exception;
	//비밀번호 찾기
	public MemberDTO findPwd(MemberDTO member) throws Exception;
	//회원정보 보기
	public MemberDTO view(MemberDTO member)throws Exception;
	//회원정보 수정
	public int update(MemberDTO member)throws Exception;
	// 회원 탈퇴
	public int delete(MemberDTO member)throws Exception;
	//전체 목록 보기
	public List<MemberDTO> getMemberList(MemberDTO member)throws Exception;
	//검색된 전체 건수 얻기
	public int totalCount(MemberDTO member)throws Exception;
	//회원 체크박스 삭제
	public int memberDeletes(Map<String, Object> params)throws Exception;

	public List<MemberDTO> getMemberListBoforeN(MemberDTO member)throws Exception;
}
