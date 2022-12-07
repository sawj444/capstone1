package com.product.joljak.controller;

import com.product.joljak.dto.BoardDto;
import com.product.joljak.entity.Board;
import com.product.joljak.repository.BoardRepository;
import com.product.joljak.service.BoardService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@NoArgsConstructor
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String course(Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> list = boardService.boardList(pageable);

        model.addAttribute("list", list);
        return "course";
    }

    @GetMapping("/main")
    public String main(Model model, @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> list = boardService.boardList(pageable);

        model.addAttribute("list", list);
        return "main";
    }

    @GetMapping("/board")
    public String board(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam(required = false,defaultValue = "")String searchText) {

        Page<Board> list = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 2, 1);
        int endPage = Math.min(nowPage + 2, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "board/board";
    }

    @GetMapping("/boardwrite")
    public String boardwrite() {
        return "board/board_write";
    }

    @PostMapping("/boardwritepro")
    public String boardWritePro(BoardDto boardDto) {
        boardService.write(boardDto);
        return "redirect:board";
    }



    // 조회수 증가 적용
    @GetMapping("/board/view")
    public String boardDetail(Model model, Long id) {
        Board board = boardService.boardView(id);
        boardService.updateView(id);
        model.addAttribute("board", board);
        return "board/boardview";
    }




}
