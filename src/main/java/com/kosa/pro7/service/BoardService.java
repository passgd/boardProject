package com.kosa.pro7.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kosa.pro7.domain.BoardDTO;
import com.kosa.pro7.mappers.BoardDAO;

@Service
public class BoardService {
	
	@Autowired
	public  BoardDAO boardDAO;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
			result.put("list", boardDAO.getBoardList(board));
			result.put("board", board);
		} catch (Exception e) {
			result.put("message", "서버에 오류 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//게시판 등록
	public boolean insert(BoardDTO board)throws Exception {
		System.out.println("board.service.boardInsert() 함수 호출됨");
		return 0 != boardDAO.insert(board);
	}
	
	//게시판 수정
	public boolean update(BoardDTO board)throws Exception {
		System.out.println("board.service.boardUpdate() 함수 호출됨");
		return 0 != boardDAO.update(board);
	}
	
	//게시판 삭제
	@Transactional
	public boolean delete(int boardid)throws Exception {
		System.out.println("board.service.boardDelete() 함수 호출됨");
		boolean result =  boardDAO.delete(boardid);
		System.out.println("서비스 확인: " + result);
		return  result;
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
	
	//전체 삭제
	public int boardDeleteAll(Map<String, Object> params) throws Exception{
		System.out.println("BoardService.boardDeleteAll()" + params);				
		
		//체크한 게시판 boardid 목록
		List<String> idsList = (List<String>) params.get("ids");
		System.out.println("ids  = " + idsList	);
		
		//'Y'로 업데이할 게시판 boardid 목록
		List<String> idsToUpdate = new ArrayList<>();
		
		//'Y'로 업데이트할 boardid 수를 제한 하기 위한 N값 이용
		int N = idsList.size();
		System.out.println("idSize = " + N);
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setN(N);
		
		int updateCount = 0;
		
		for(String boardid : idsList) {
			//N개 이상 업데이트 하지 않도록 제한
			if(updateCount >= N) {
				break;
			}		
		
		//boardid가 'N'이면 'Y' 업데이트 대상
		if(idsList.contains(boardid)) {
			idsToUpdate.add(boardid);
			updateCount++;
		}
	}
		
		//'Y'로 업데이트할 boardid가 있으면 작업 수행
		if(!idsToUpdate.isEmpty()) {
			params.put("boardDelete", idsToUpdate);
			int updateRows = boardDAO.boardDeleteAll(params);
			
			return updateRows;
		}else {	
			return 0;
		}		
	}
	
	//삭제 배열 가져오기
	public List<BoardDTO> getBoardList(BoardDTO board)throws Exception{
		System.out.println("BoardService.getBoardList()");
		
		String[] ids = board.getIds();
		
		Map<String, Object> params = new HashMap<>();
		
		params.put("boardid", board.getBoardid());
		
		params.put("N", board.getN());
		
		// deleteY_N 열이 'Y'인 항목만 가져오기.
		params.put("delete_YN", 'Y');
		
		return boardDAO.getboardListBoforeN(params);
	}
	
	//답글
	public int reply(BoardDTO board)throws Exception {
		
		int level = board.getLevel(); //부모 게시물의 level
		board.setPid(board.getBoardid()); //부모 게시물의 boardid를 pid로 설정
		board.setLevel(level + 1); // 부모 게시물의 level + 1 설정
		int result = boardDAO.insertBoardReply(board);
				
		return result;
	}
	
	//메일
	public void sendMail(String to) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("관리자<asdf303869@gmail.com>");
		message.setTo(to);
		message.setSubject("답변!");
		message.setText("회원님의 게시물에 답변이 달렸습니다.");
		javaMailSender.send(message);
	}
	
}
