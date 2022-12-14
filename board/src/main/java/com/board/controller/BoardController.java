package com.board.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.board.domain.BoardVO;
import com.board.domain.Page;
import com.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	BoardService service;

	// 게시물 목록
	@GetMapping("/list") // Get방식은 void
	public void getList(Model model) throws Exception {
		List<BoardVO> list = null;
		list = service.list();
		model.addAttribute("list", list);
	}

	// 게시물 작성 (화면)
	@GetMapping("/write")
	public void getWrite() throws Exception {
	}

	// 게시물 작성 (값 저장)
	@PostMapping("/write") // Post방식은 String
	public String postWrite(BoardVO vo) throws Exception {
		service.write(vo);
		return "redirect:/board/list";
	}

	// 게시물 조회
	@GetMapping("/view")
	public void getView(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);

	}

	// 게시물 수정 (값 가져오기)
	@GetMapping("/modify")
	public void getModify(@RequestParam("bno") int bno, Model model) throws Exception {
		BoardVO vo = service.view(bno);
		model.addAttribute("view", vo);
	}

	// 게시물 수정 (가져온값 수정해서 내보내기)
	@PostMapping("/modify")
	public String postModify(BoardVO vo) throws Exception {
		service.modify(vo);
		return "redirect:/board/view?bno=" + vo.getBno();
	}

	@GetMapping("/delete")
	public String getDelete(@RequestParam("bno") int bno) throws Exception {
		service.delete(bno);
		return "redirect:/board/list";
	}

	// 게시물 목록+ 페이징 추가
	@GetMapping("/listPage")
	public void getListPage(Model model, @RequestParam("num") int num) throws Exception {
		Page page = new Page();

		page.setNum(num);
		page.setCount(service.count());

		List<BoardVO> list = null;
		list = service.listPage(page.getDisplayPost(), page.getPostNum());
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);
	}

	// 게시물 목록 + 페이징 추가 + 검색
	@RequestMapping(value = "/listPageSearch", method = RequestMethod.GET)
	public void getListPageSearch(Model model, @RequestParam("num") int num,
			@RequestParam(value = "searchType", required = false, defaultValue = "title") String searchType,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword) throws Exception {

		Page page = new Page();

		page.setNum(num);
//		page.setCount(service.count());
		page.setCount(service.searchCount(searchType, keyword));

		// 검색 타입과 검색어
		// page.setSearchTypeKeyword(searchType, keyword);
		page.setSearchType(searchType);
		page.setKeyword(keyword);

		List<BoardVO> list = null;
		// list = service.listPage(page.getDisplayPost(), page.getPostNum());
		list = service.listPageSearch(page.getDisplayPost(), page.getPostNum(), searchType, keyword);

		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("select", num);

		model.addAttribute("searchType", searchType);
		model.addAttribute("keyword", keyword);

	}
}
