package com.changon.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.changon.board.domain.BoardDTO;

@Mapper
public interface BoardMapper {

	public int insertBoard(BoardDTO params);

	public BoardDTO selectBoardDetail(Long idx);

	public int updateBoard(BoardDTO params);

	public int deleteBoard(Long idx);

	public List<BoardDTO> selectBoardList(BoardDTO params);

	public int selectBoardTotalCount(BoardDTO params); // 삭제여부(delete_yn)가 'N'으로 지정된 게시글 개수 조회

}
