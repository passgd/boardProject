package com.kosa.pro7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosa.pro7.domain.CommentDTO;
import com.kosa.pro7.mappers.CommentDAO;

@Service
public class CommentService {

	@Autowired
	public CommentDAO commentDAO;
	
	//댓글 등록
	public boolean insert(CommentDTO comment)throws Exception{
		System.out.println("Service의 댓글 등록");
		return 0 != commentDAO.insertComment(comment);
	}
}
