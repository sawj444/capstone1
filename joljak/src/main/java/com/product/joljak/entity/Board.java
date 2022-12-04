package com.product.joljak.entity;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@NoArgsConstructor(access=AccessLevel.PROTECTED)
public class Board extends TimeEntity{
    @Id
    @GeneratedValue
    private Long id;

//    @NotEmpty(message = "제목은 비워둘 수 없습니다.")
    @Column(length = 100,nullable = false)
    private String title;
//    private String type;

//    @NotEmpty(message = "내용은 비워둘 수 없습니다.")
    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

//    @Column(length = 10,nullable = false)
    private String nickname;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views;

    //    private Integer referrals;

    @Builder
    public Board(Long id, String title, String content, String nickname) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }


//    public Board() {
//    }
//
//    public Board(int b_num, String type, String content, String nickname, Date regdate, Date updatedate, int views, int referrals) {
//        this.b_num = b_num;
//        this.type = type;
//        this.content = content;
//        this.nickname = nickname;
//        this.regdate = regdate;
//        this.updatedate = updatedate;
//        this.views = views;
//        this.referrals = referrals;
//    }
}
