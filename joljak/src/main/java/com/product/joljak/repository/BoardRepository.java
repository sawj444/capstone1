package com.product.joljak.repository;

import com.product.joljak.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {


    List<Board> findAll();

    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);



    //  조회수 증가
    @Modifying
    @Query("update Board b set b.views = b.views + 1 where  b.id=:id")
    int updateView(@Param("id") Long id);
}
