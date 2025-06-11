package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TBL_BOARD")
public class Board {

    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String boardTitle;
    private String boardContent;

//  joincolumn 외래키 -> 객체명_ID 디폴트값으로 이렇게 정의가 된다.
//  USER_ID
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
}

