package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "TBL_ORDER")
@Getter @Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(
        name = "SEQ_ORDER_GENERATOR",
        sequenceName = "SEQ_ORDER"
)
public class Order {

    //    @Id @GeneratedValue(generator = "SEQ_ORDER_GENERATOR")
//    @EqualsAndHashCode.Include
    private Long id;
    private Integer amount;

    @Id @ManyToOne @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @Id @ManyToOne @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}
