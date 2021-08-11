package com.changon.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.changon.board.domain.AttachDTO;

@Mapper
public interface AttachMapper {
	
	/*
	 * id : 파일 번호
	 * boardIdx : 게시글 번호
	 */
	
	public int insertAttach(List<AttachDTO> attachList);
	
	public AttachDTO selectAttachDetail(Long id);
	
	public int deleteAttach(Long boardIdx);
	
	public List<AttachDTO> selectAttachList(Long boardIdx);
	
	public int selectAttachTotalCount(Long boardIdx);

}
