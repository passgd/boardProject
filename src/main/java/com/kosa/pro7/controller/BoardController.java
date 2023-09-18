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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.pro7.domain.BoardDTO;
import com.kosa.pro7.service.BoardService;

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
public class BoardController {
	
	@Autowired
	private BoardService service;

		//게시판 페이징
		@ResponseBody
		@RequestMapping(value="/board/ajaxList.do", method = RequestMethod.POST)
		public Map<String, Object> ajaxlist2(@RequestBody BoardDTO board, HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("board.controller.BoardajaxList2() ");
			Map<String, Object> result = new HashMap<String, Object>();
			try {
				List<BoardDTO> boardList = service.boardPageList2(board);
				result.put("status", true);
				result.put("boardList", boardList);
			} catch (Exception e) {
				result.put("status", true);
				result.put("message", "서버에 오류 발생");
				e.printStackTrace();
			}
			System.out.println(result);
			return result;
		}

		
		//게시판 전체보기
		@GetMapping("/board/list.do")
		public String list(BoardDTO board, Model model) throws Exception {
			System.out.println("board.controller.BoardList() ");
			
			try {
				model.addAttribute("boardList", service.boardPageList(board));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "board/boardList";
		}
		
		//게시판 상세보기
		@RequestMapping(value="/board/view.do", method= RequestMethod.GET)
		public String view(BoardDTO board, Model model) throws Exception {
			System.out.println("board.controller.BoardView()");
			
			try {
				model.addAttribute("status", true);
				BoardDTO boardView = service.view(board.getBoardid());
				model.addAttribute("board", boardView);
			} catch (Exception e) {
				model.addAttribute("status", false);
				model.addAttribute("message", "서버에 오류 발생");
				e.printStackTrace();
			}
			return "board/view";
		}
		
		public String ajaxview(BoardDTO board, HttpServletRequest request, HttpServletResponse response) throws Exception {
			JSONObject result = new JSONObject();
			System.out.println("board.controller.BoardAjaxView()");
			
			try {
				service.boardCount(board.getBoardid());
				
				BoardDTO content = service.view(board.getBoardid());
				result.put("board", content);
				result.put("boardid", content.getBoardid());
				result.put("title", content.getTitle());
				result.put("contents", content.getContents());
				result.put("email", content.getEmail());
				result.put("reg_date", content.getReg_date());
				result.put("view_count", content.getView_count());
				System.out.println("상세보기 확인: " + content);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result.toString();
		}
		
		//게시판 등록
		public String insertForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	System.out.println("board.controller.insertForm() ");
			
	    	return "board/boardinsert.jsp";
		}
		public String insert(BoardDTO board, HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("board.controller.BoardInsert()");

			JSONObject jsonResult = new JSONObject();
			boolean status = service.insert(board);
			
			jsonResult.put("status", status);
			jsonResult.put("message", status ? "공지사항 글 작성이 등록되었습니다" : "공지사항 글 작성시 오류가 발생하였습니다");		
			return jsonResult.toString();
		}
		
		//게시판 수정
		public String updateForm(BoardDTO board, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	System.out.println("board.controller.updateForm() ");
			
			try { 
				request.setAttribute("board", service.view(board.getBoardid()));
	        } catch (Exception e) { 
	        	e.printStackTrace();
	        }
	    	return "board/boardupdate.jsp";
		}
		public String update(BoardDTO board, HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("board.controller.BoardUpdate()");
			JSONObject jsonResult = new JSONObject();
			boolean status = service.update(board);
			
			jsonResult.put("status", status);
			jsonResult.put("message", status ? "공지사항 글 수정이 되었습니다" : "공지사항 글 수정시 오류가 발생하였습니다");
			
			return jsonResult.toString();
		}

		//게시판 삭제
		public String delete(BoardDTO board, HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("board.controller.BoardDelete()");
			JSONObject jsonResult = new JSONObject();
			
			boolean status = service.delete(board.getBoardid());
			
	    	jsonResult.put("status", status);
	    	jsonResult.put("message", status ? "공지 사항 글이 삭제되었습니다" : "공지사항 글 삭제시 오류가 발생하였습니다");

	    	return jsonResult.toString();
		}

		//게시판 전체 삭제
		public String deleteAll(String[] boardids, HttpServletRequest request, HttpServletResponse response) throws Exception {
			System.out.println("board.controller.BoardDeleteAll()");
			JSONObject jsonResult = new JSONObject();
			
			boolean status = service.deleteAll(boardids);
			
			jsonResult.put("status", status);
			jsonResult.put("message", status ? "글이 삭제되었습니다" : "오류가 발생하였습니다. 다시 시도해주세요.");

	    	return jsonResult.toString();
		}
		
}
