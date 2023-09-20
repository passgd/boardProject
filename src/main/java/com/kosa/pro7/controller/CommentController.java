package com.kosa.pro7.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kosa.pro7.domain.CommentDTO;
import com.kosa.pro7.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService service;
	
	//등록
	@PostMapping("/insert")
	@ResponseBody
	public Map<String, Object> insert(@RequestBody CommentDTO comment)throws Exception{
		System.out.println("댓글 등록 Controller");
		
		Map<String, Object> result = new HashMap<>();
		
		if(service.insert(comment)) {
			result.put("message", "댓글 등록 완료!!!");
		}else {
			result.put("message", "댓글 등록 실패!!");
		}
		return result;
	}
	
}
