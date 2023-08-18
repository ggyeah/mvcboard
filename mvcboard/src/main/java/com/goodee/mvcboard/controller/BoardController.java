package com.goodee.mvcboard.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.goodee.mvcboard.service.BoardService;
import com.goodee.mvcboard.vo.Board;

import lombok.extern.slf4j.Slf4j;

/*
ANSI_RESET = "\u001B[0m";
ANSI_CYAN = "\u001B[36m";
*/

//slf4j : system.out.print대신 사용
@Slf4j
@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	//delete 폼-------------------------------------
	@GetMapping("/board/removeBoard")
	public String removeBoard(@RequestParam(value = "boardNo", defaultValue = "1") int boardNo, Model model) {
		model.addAttribute("boardNo", boardNo);
		return "/board/removeBoard";
	}
	//delete 액션
	@PostMapping("/board/removeBoard")
	public String deleteBoard(HttpServletRequest request, @RequestParam(value = "boardNo", defaultValue = "1") int boardNo) {
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.removeBoard(boardNo, path);
		log.debug("CYAN"+"row :"+row +"RESET");
		return "redirect:/board/boardList";
	}
	
	
	//modify입력 폼------------------------------------
	@GetMapping("/board/modifyBoard")
	public String updateBoard(Model model,
			@RequestParam(value = "boardNo", defaultValue = "1") int boardNo) {
		Map<String, Object> boardOne= boardService.getBoardOne(boardNo);
	    //view로 넘길때는 다시 분리해서
		model.addAttribute("fileList", boardOne.get("fileList"));
		model.addAttribute("board", boardOne.get("board"));
		return "/board/modifyBoard";
	}
	
	//modify입력 액션
	@PostMapping("/board/modifyBoard")
	public String updateBoard(Board board, HttpServletRequest request) {
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.modifyBoard(board, path);
		log.debug("CYAN"+"row :"+row +"RESET");
		return "redirect:/board/boardList";
	}
	
	//modify안에서 boardfile 삭제
	@GetMapping("/board/deleteBoardfile")
	public String updateBoard(HttpServletRequest request,  @RequestParam(value = "boardfileNo", defaultValue = "1") int boardfileNo, @RequestParam(value = "boardNo", defaultValue = "1") int boardNo) {
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.deleteBoardfile(boardfileNo, boardNo, path);
		log.debug("CYAN"+"row :"+row +"RESET");
		return "redirect:/board/boardOneList?boardNo=" + boardNo;
	}
	
	//insert입력 폼--------------------------------------
	@GetMapping("/board/addBoard")
	public String addBoard() {
		return "/board/addBoard";
	}
	
	//insert입력 액션
	@PostMapping("/board/addBoard")
	public String addBoard(HttpServletRequest request, Board board) { //매개값 request객체를 받는다 <- request api 직접호출
		String path = request.getServletContext().getRealPath("/upload/");
		int row = boardService.addBoard(board, path);
		log.debug("row :"+row);
		return "redirect:/board/boardList";
	}
	
	//게시글 상세보기------------------------------------
	@GetMapping("/board/boardOneList")
	public String boardOneList(Model model,
			@RequestParam(value = "boardNo", defaultValue = "1") int boardNo) {
	    Map<String, Object> boardOne= boardService.getBoardOne(boardNo);
	    //view로 넘길때는 다시 분리해서
		model.addAttribute("fileList", boardOne.get("fileList"));
		model.addAttribute("board", boardOne.get("board"));

	    return "/board/boardOneList";
	}
	
	//리스트 출력----------------------------------------
	@GetMapping("/board/boardList")
	public String boardList(Model model, 
							@RequestParam(name="currentPage", defaultValue = "1") int currentPage,
							@RequestParam(name="rowPerPage", defaultValue = "10") int rowPerPage,
							@RequestParam(name="localName", required = false) String localName)  {
		
		System.out.println("row:"+"localName :"+localName);
		Map<String, Object> resultMap = boardService.getBoardList(currentPage,rowPerPage,localName);
		
		//view로 넘길때는 다시 분리해서
		model.addAttribute("localName", resultMap.get("localName"));
		model.addAttribute("boardList", resultMap.get("boardList"));
		model.addAttribute("lastPage", resultMap.get("lastPage"));
		model.addAttribute("localNameList", resultMap.get("localNameList"));
		model.addAttribute("currentPage", currentPage);
		
		return "/board/boardList";
	}
}
