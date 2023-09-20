package com.kosa.pro7.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
	
	private int commentid; //댓글 번호
	private int boardid; //게시판 번호
	private String email; // 아이디
	private String contents; //내용
	private Date reg_date; //작성시간
	private String delete_yn; 	// 삭제유무 
}
