package com.changon.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.changon.board.constant.Method;
import com.changon.board.domain.BoardDTO;
import com.changon.board.service.BoardService;
import com.changon.board.util.UiUtils;

@Controller
public class BoardController extends UiUtils {
	
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
	public String registerBoard(final BoardDTO params, Model model) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if(isRegistered == false) {
				return showMessageWithRedirect("게시글 등록에 실패하였습니다.", "/board/list.do", Method.GET, null, model);
			}
		}catch(DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제 발생.", "/board/list.do", Method.GET, null, model);
		}catch(Exception e) {
			return showMessageWithRedirect("시스템에 문제 발생.", "/board/list.do", Method.GET, null, model);
		}
		return showMessageWithRedirect("게시글 등록 완료", "/board/list.do", Method.GET, null, model);
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
	public String deleteBoard(@RequestParam(value = "idx", required = false) Long idx, Model model) {
		if(idx == null) {
			return showMessageWithRedirect("올바르지 않은 접근.", "/board/list.do", Method.GET, null, model);
		}
		try {
			boolean isDeleted = boardService.deleteBoard(idx);
			if(isDeleted == false) {
				showMessageWithRedirect("게시글 삭제 실패.", "/board/list.do", Method.GET, null, model);
			}
			
		}catch(DataAccessException e) {
			return showMessageWithRedirect("데이터베이스 처리 과정에 문제 발생.", "/board/list.do", Method.GET, null, model);
		}catch(Exception e) {
			return showMessageWithRedirect("시스템에 문제 발생.", "/board/list.do", Method.GET, null, model);
		}
		
		return showMessageWithRedirect("게시글 삭제 완료", "/board/list.do", Method.GET, null, model);
	}
}
