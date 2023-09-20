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
	public int insert(BoardDTO board)throws Exception;
	
	//게시판 수정
	public int update(BoardDTO board)throws Exception;
	
	//게시판 삭제
	public boolean delete(int boardid)throws Exception;
	
	//게시판 상세보기
	public BoardDTO view(int boardid)throws Exception;
	
	//게시판 Top5
	public List<BoardDTO> boardTop()throws Exception;
	
	//조회수
	public int boardCount(int boardid)throws Exception;
	
	//검색된 전체 건수 얻는다
	public int totalCount(BoardDTO board)throws Exception;
	
	//페이징
	public List<BoardDTO> getBoardList(BoardDTO board)throws Exception;
	
	//체크박스 게시글 삭제
	public int boardDeleteAll(Map<String, Object> params)throws Exception;

	//글 삭제 후 10건 만들기
	public List<BoardDTO> getboardListBoforeN(Map<String, Object> params) throws Exception;
	
	
	//답글
	public int insertBoardReply(BoardDTO board)throws Exception;
	
}
