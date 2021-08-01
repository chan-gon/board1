package com.changon.board;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.changon.board.domain.BoardDTO;
import com.changon.board.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
public class MapperTests {
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("등록 테스트 제목");
		params.setContent("등록 테스트 내용");
		params.setWriter("등록 테스트 작성자");
		
		int result = boardMapper.insertBoard(params);
		System.out.println(result + "건 등록 완료");
	}
	
	@Test
	public void testOfSelectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long) 1);
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			String boardJson = objectMapper.writeValueAsString(board);
			
			System.out.println("=====================");
			System.out.println(boardJson);
			System.out.println("=====================");
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("게시글 수정 테스트");
		params.setContent("게시글 수정 테스트");
		params.setWriter("게시글 수정 테스트");
		params.setIdx((long) 1);
		
		int result = boardMapper.updateBoard(params);
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				String boardJson = objectMapper.writeValueAsString(board);
				
				System.out.println("=====================");
				System.out.println(boardJson);
				System.out.println("=====================");
			}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfDelete() {
		int result = boardMapper.deleteBoard((long) 1);
		if(result == 1) {
			BoardDTO board = boardMapper.selectBoardDetail((long) 1);
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.registerModule(new JavaTimeModule());
				objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
				String boardJson = objectMapper.writeValueAsString(board);
				
				System.out.println("=====================");
				System.out.println(boardJson);
				System.out.println("=====================");
			}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testMultipleInsert() {
		for(int i = 2; i<=20; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle(i + "번 게시글 제목");
			params.setContent(i + "번 게시글 내용");
			params.setWriter(i + "번 게시글 작성자");
			int result = boardMapper.insertBoard(params);
			System.out.println(result + "개 등록 완료");
		}
	}
	
	@Test
	public void testSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if(boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			if(CollectionUtils.isEmpty(boardList) == false) {
				for(BoardDTO board : boardList) { // forEach
					System.out.println("=====================");
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println(board.getWriter());
					System.out.println("=====================");
				}
			}
		}
	}

}
