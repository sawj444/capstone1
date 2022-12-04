package com.product.joljak.dto;

import com.product.joljak.entity.Board;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {
    private Long id;

    private String title;
//    private String type;

    private String content;
    private String nickname;
    private LocalDateTime regdate;
    private LocalDateTime modifiedDate;
    //    private Date updatedate;
    private int views;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .nickname(nickname)
                .title(title)
                .content(content)
                .build();
        return build;
    }


    @Builder
    public BoardDto(Long id, String title, String content, String nickname, LocalDateTime regdate, LocalDateTime modifiedDate, int views) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.regdate = regdate;
        this.modifiedDate = modifiedDate;
        this.views = views;
    }
}
