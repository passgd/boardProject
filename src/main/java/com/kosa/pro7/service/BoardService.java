package com.kosa.pro7.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosa.pro7.domain.BoardDTO;
import com.kosa.pro7.mappers.BoardDAO;

@Service
public class BoardService {
	
	@Autowired
	public  BoardDAO boardDAO;
	
	//전체 목록 보기
//	public List<BoardDTO> boardAllList()throws Exception{
//		return boardDAO.boardAllList();
//	}
	
	//1. 공지사항 페이지 목록 조회
	// 인자 : 페이지 번호
	//        검색키(X)
	public Map<String, Object> boardPageList(BoardDTO board)throws Exception{
		System.out.println("BoardService.boardPageList() 함수 호출");
		//1.전체 건수를 얻기
		board.setTotalCount(boardDAO.totalCount(board));
		
		Map<String, Object> result = new HashMap<>();
		
		try {
			
		} catch (Exception e) {
			
		}
		
		return result;
	}
	
	//게시판 등록
	public boolean insert(BoardDTO board)throws Exception {
		System.out.println("board.service.boardInsert() 함수 호출됨");
		return boardDAO.insert(board);
	}
	
	//게시판 수정
	public boolean update(BoardDTO board)throws Exception {
		System.out.println("board.service.boardUpdate() 함수 호출됨");
		return boardDAO.update(board);
	}
	
	//게시판 삭제
	public boolean delete(int boardid)throws Exception {
		System.out.println("board.service.boardDelete() 함수 호출됨");
		return boardDAO.delete(boardid);
	}
	//게시판 전체 삭제
	public boolean deleteAll(String[] boardids) throws Exception{
		System.out.println("board.service.boardDeleteAll() 함수 호출됨");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", boardids);
		return 0 != boardDAO.deleteAll(params);
	}
	//게시판 상세보기
	public BoardDTO view(int boardid)throws Exception{
		System.out.println("board.service.boardView() 함수 호출됨");
		return boardDAO.view(boardid);
	}
	
	//게시판 Top5
	public List<BoardDTO> boardTop()throws Exception{
		System.out.println("board.service.boardTop5() 함수 호출됨");
		return boardDAO.boardTop();
	}
	
	//조회수
	public int boardCount(int boardid)throws Exception {
		System.out.println("board.service.boardCount() 함수 호출됨");
		return boardDAO.boardCount(boardid);
	}

	//더보기
//	public List<BoardDTO> boardPageList2(BoardDTO board) throws Exception{
//		return boardDAO.getBoardList2(board);
//	}
}
