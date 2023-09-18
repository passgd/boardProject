package com.kosa.pro7;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.kosa.pro7.domain.BoardDTO;
import com.kosa.pro7.domain.NoticeDTO;
import com.kosa.pro7.service.BoardService;
import com.kosa.pro7.service.NoticeService;

@Controller
public class MainController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeService noticeService;
	
	@GetMapping("/")
	public String home(HttpServletRequest req, HttpServletResponse res)throws Exception{
		System.out.println("main() invoked. ");
		
		try {
			List<NoticeDTO> noticeTop5 = noticeService.noticeTop();
			req.setAttribute("noticeTop5", noticeTop5);
			
			List<BoardDTO> boardTop5 = boardService.boardTop();
			req.setAttribute("boardTop5", boardTop5);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "main";
	}

}
