package com.changon.board.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.changon.board.adaptor.GsonLocalDateTimeAdaptor;
import com.changon.board.domain.CommentDTO;
import com.changon.board.service.CommentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@RestController
public class CommentController {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/comments/{boardIdx}")
	public JsonObject getCommentList(@PathVariable("boardIdx") Long boardIdx, @ModelAttribute("params") CommentDTO params) {

		JsonObject jsonObj = new JsonObject();

		List<CommentDTO> commentList = commentService.getCommentList(params);
		if (CollectionUtils.isEmpty(commentList) == false) {
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdaptor()).create();
			JsonArray jsonArr = gson.toJsonTree(commentList).getAsJsonArray();
			jsonObj.add("commentList", jsonArr);
		}

		return jsonObj;
	}

}
