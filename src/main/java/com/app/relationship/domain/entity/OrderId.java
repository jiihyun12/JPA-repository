package com.app.relationship.domain.entity;

// 1. 반드시 기본 생성자
// 2. 반드시 HashCone(), Eqquals()가 재정의되어있어ㅑㅇ 한다.
// 3. seri

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OrderId implements Serializable {

//    1. 반드시 기본 생성자
//    2. 반드시 HashCode(), Equals()가 재정의 되어있어야 한다.
//    3. Serializable 구현, 직렬화 인터페이스

    private Long member;
    private Long product;
}
