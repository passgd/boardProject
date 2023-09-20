package com.kosa.pro7.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.kosa.pro7.domain.CommentDTO;

@Mapper
public interface CommentDAO {
	
	//댓글 등록
	public int insertComment(CommentDTO comment)throws Exception;
	

}
