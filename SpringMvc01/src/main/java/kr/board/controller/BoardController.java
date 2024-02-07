package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController { // new BoardController();
// method 입력
	// boardList.do
	@Autowired
	private BoardMapper mapper;
	
	// HandlerMapping
	// 객체바인딩 : Model
	@RequestMapping("/boardList.do")
	public String boardList(Model model) {
/*
		Board vo=new Board();
		vo.setIdx(1);
		vo.setTitle("게시판실습");
		vo.setContent("게시판실습");
		vo.setWriter("김환용");
		vo.setIndate("2024-02-02");
		vo.setCount(0);
		List<Board> list=new ArrayList<Board>();
		list.add(vo);
		list.add(vo);
		list.add(vo);
*/
		List<Board> list=mapper.getLists();
		//jsp에다가 데이터를 넘겨주기 위해 Model이라는 가상의 메모리에 객체바인딩 해야 함
		model.addAttribute("list", list);
		
		return "boardList"; 
		 // 해당 return을 하면은 viewResolver를 통하여 연계하여 포워딩함(접두사/접미사 조합)
		 // /WEB-INF/views/boardList.jsp --> forward
	}
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm"; // /WEB-INF/views/boardForm.jsp --> forward
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board vo) { // title, content, writer => 파라미터수집(Board)
		mapper.boardInsert(vo); // 등록
		return "redirect:/boardList.do"; // redirect
	}
	
	@GetMapping("/boardContent.do")
	public String boardContent(@RequestParam("idx") int idx, Model model) { // ?idx=6
		Board vo=mapper.boardContent(idx);
		// 조회수 증가
		mapper.boardCount(idx);
		model.addAttribute("vo", vo); // ${vo.idx}...
		return "boardContent"; // boardContent.jsp
	}
	
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) { // ?idx=6
		mapper.boardDelete(idx); // 삭제
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) { // ?idx=6
		Board vo=mapper.boardContent(idx);
		model.addAttribute("vo", vo); // ${vo.idx}..
		return "boardUpdate"; // boardUpdate.jsp
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board vo) { // idx, title, content => 파라미터수집(Board)
		mapper.boardUpdate(vo); // 수정
		return "redirect:/boardList.do"; // redirect
	}
	
}
