package com.kosa.pro7.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.pro7.domain.BoardDTO;
import com.kosa.pro7.service.BoardService;

import oracle.jdbc.proxy.annotation.Post;

/*
	더 보기
	ajax -> 서버에서 html생성해서 클라이언트에서 출력
		 -> 순수 DATA만 서버에서 전달하고 클라이언트에서 HTML 생성 출력
	1. DB에 자료를 얻는 방법
		1. 처음 10건 추출하는 방법
		2. 다음 10건 얻는 방법
	2.순수 DATA만 서버에 전달
		
	3. 클라이언트에서 전달받는 Data을 이용해서 HTML 출력
*/
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;

		//게시판 페이징
		@RequestMapping("/list")
		public String list(BoardDTO board, Model model) throws Exception {
			System.out.println("board.controller.BoardajaxList2() ");
			
			try {
				model.addAttribute("result", service.boardPageList(board));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "board/list";
		}
	
		//게시판 상세보기
		@PostMapping("/view")
		@ResponseBody
		public BoardDTO view(@RequestBody BoardDTO board) throws Exception {
			System.out.println("board.controller.BoardView()");
			
			BoardDTO boardView = service.view(board.getBoardid());
			
			service.boardCount(board.getBoardid());
			
			System.out.println(boardView);
					
			return boardView;
		}
		
		//게시판 등록
		@PostMapping("/insert")
		@ResponseBody
		public Map<String, Object> insert(@RequestBody BoardDTO board) throws Exception {
			System.out.println("board.controller.BoardInsert()");

			Map<String, Object> result = new HashMap<>();
			
			System.out.println("등록 확인: " + result);
			if(service.insert(board)) {
				result.put("message", "게시판 글 등록이 완료 되었습니다.");
			}else {
				result.put("message", "게시판 글 등록 중 오류가 발생했습니다.");
			}
			return result;
		}
		
		//게시판 수정
		@PostMapping("/update")
		@ResponseBody
		public Map<String, Object> update(@RequestBody BoardDTO board) throws Exception {
			System.out.println("board.controller.BoardUpdate()");
			
			Map<String, Object> result = new HashMap<>();
			
			boolean update = service.update(board);
			
			if(update) {
				result.put("message", "성공적으로 게시판 글이 수정 되었습니다.");
				result.put("boardUpdate", update);
			}else {
				result.put("message", "글 수정중 오류가 발생했습니다.");
			}
			return result;
		}

		//게시판 삭제
		@PostMapping("/delete")
		@ResponseBody
		public Map<String, Object> delete(@RequestBody BoardDTO board) throws Exception {
			System.out.println("board.controller.BoardDelete()");

			Map<String, Object> result = new HashMap<>();
			
			boolean deleteResult = service.delete(board.getBoardid());
			
			System.out.println("삭제 확인: " + deleteResult);
			
			if(deleteResult) {
				result.put("message", "게시판 글 삭제가 완료 되었습니다.");
			}else {
				result.put("message", "게시판 글 삭제중 오류가 발생했습니다.");
			}
			return result;
		}

		//게시판 전체 삭제
		@PostMapping("/checkDelete")
		@ResponseBody
		public Map<String, Object> deleteAll(@RequestBody Map<String, Object> params) throws Exception {
			System.out.println("board.controller.BoardDeleteAll()");
			
			Map<String, Object> result = new HashMap<>();
			
			List<String> idsList = (List<String>) params.get("ids");
			
			//삭제된 게시판 boardid 목록 얻기
			int deleteBoardIds = service.boardDeleteAll(params);
			System.out.println("deleteBoard cnt = " + deleteBoardIds);
			
			if(deleteBoardIds > 0) {
				result.put("status", true);
				result.put("message", "게시판 삭제가 완료 되었습니다.");
				
				//마지막 삭제 boardid 가져오기
				String lastDeleteBoardid = idsList.get(idsList.size() -1 );
				
				//서비스를 통해 삭제된 게시판 목록 가져오기
				int N = deleteBoardIds;
				BoardDTO boardDTO = new BoardDTO();
				boardDTO.setIds(new String[] {lastDeleteBoardid});
				boardDTO.setN(N);
				boardDTO.setBoardid(Integer.parseInt((String) params.get("boardid")));
				
				List<BoardDTO> pageBoardList = service.getBoardList(boardDTO);
				System.out.println("pageBoardList = " + pageBoardList);
				System.out.println(pageBoardList);
				result.put("boardList", pageBoardList);
			}else {
				result.put("message", "게시판 삭제 중 오류가 발생했습니다.");
			}
			return result;
		}
		
		//답글
		@PostMapping("/reply")
		@ResponseBody
		public Map<String, Object> reply(@RequestBody BoardDTO board, Model model) throws Exception{
			System.out.println("board.controller.BoardReply()");
			
			Map<String, Object> response = new HashMap<>();

			int result = service.reply(board);
			
			if (result > 0 ){
				response.put("message", "답변 등록에 성공했습니다.");
				
				String AutoEmail = "yso3038@naver.com";

				service.sendMail(AutoEmail);
			}else {
				response.put("message", "답변 등록에 실패하였습니다.");
			}
			return response;
		}
		
}
