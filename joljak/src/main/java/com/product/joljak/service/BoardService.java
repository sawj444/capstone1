package com.product.joljak.service;

import com.product.joljak.dto.BoardDto;
import com.product.joljak.entity.Board;
import com.product.joljak.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class  BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 리스트
    @Transactional
    public Page<Board> boardList(Pageable pageable) {
//        List<Board> boards = boardRepository.findAll();
//        List<BoardDto> boardDtoList = new ArrayList<>();
        Page<Board> boards = boardRepository.findAll(pageable);
//        ArrayList<BoardDto> boardDtoList = new ArrayList<>();
//
//        for (Board board : boards) {
//            BoardDto boardDto = BoardDto.builder()
//                    .id(board.getId())
//                    .title(board.getTitle())
//                    .content(board.getContent())
//                    .nickname(board.getNickname())
//                    .regdate(board.getRegdate())
//                    .build();
//
//            boardDtoList.add(boardDto);
//        }
//        return boardDtoList;

        return boardRepository.findAll(pageable);
    }

    //  글쓰기
    public Long write(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    //상세페이지
    @Transactional
    public Board boardView(Long id) {
        Board board = boardRepository.findById(id).get();
//        board.updateViewCount(board.getViews());
        return board;
    }

    //조회수 증가
    @Transactional
    public int updateView(Long id) {
        return boardRepository.updateView(id);
    }


}
