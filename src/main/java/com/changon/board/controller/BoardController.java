package com.changon.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.changon.board.domain.BoardDTO;
import com.changon.board.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		
		if(idx == null) {
			model.addAttribute("board", new BoardDTO());
		}else {
			BoardDTO board = boardService.getBoardDetail(idx);
			if(board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}
		
		return "board/write";
	}
	
	@RequestMapping(value = "/board/register.do", method = RequestMethod.POST)
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if(isRegistered == false) {
				
			}
		}catch(DataAccessException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/list.do";
	}
	
	@RequestMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/board/view.do")
	public String openBoardDetail(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if(idx == null) {
			return "redirect:/board/list.do";
		}
		
		BoardDTO board = boardService.getBoardDetail(idx);
		if(board == null || "Y".equals(board.getDeleteYn())) {
			return "redirect:/board/list.do";
		}
		model.addAttribute("board",board);
		
		return "board/view";
	}
	
	@RequestMapping(value = "/board/delete.do", method = RequestMethod.POST)
	public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx) {
		if(idx == null) {
			return "redirect:/board/list.do";
		}
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if(isDeleted == false) {
				
			}
			
		}catch(DataAccessException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/board/list.do";
	}
}
