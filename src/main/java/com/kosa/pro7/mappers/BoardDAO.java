package com.kosa.pro7.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kosa.pro7.domain.BoardDTO;

@Mapper
public interface BoardDAO {

	//전체 목록 보기
//	public List<BoardDTO> boardAllList()throws Exception;
	
	//게시판 등록
	public boolean insert(BoardDTO board)throws Exception;
	
	//게시판 수정
	public boolean update(BoardDTO board)throws Exception;
	
	//게시판 삭제
	public boolean delete(int boardid)throws Exception;
	
	//게시판 상세보기
	public BoardDTO view(int boardid)throws Exception;
	
	//게시판 Top5
	public List<BoardDTO> boardTop()throws Exception;
	
	//조회수
	public int boardCount(int boardid)throws Exception;
	
	//체크박스 게시글 삭제
	public int deleteAll(Map<String, Object> params)throws Exception;
	
	//검색된 전체 건수 얻는다
	public int totalCount(BoardDTO board)throws Exception;
	
	//페이징
	public List<BoardDTO> getBoardList(Map<String, Object> result)throws Exception;

	//더보기
//	public List<BoardDTO> getBoardList2(BoardDTO board)throws Exception;

}
