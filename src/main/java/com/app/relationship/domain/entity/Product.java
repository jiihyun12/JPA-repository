package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "TBL_PRODUCT")
@Getter @Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(
        name = "SEQ_PRODUCT_GENERATOR",
        sequenceName = "SEQ_PRODUCT"
)
public class Product {

    @GeneratedValue(generator = "SEQ_PRODUCT_GENERATOR")
    @EqualsAndHashCode.Include
    @Id
    private Long id;
    private String productName;
    private Integer productPrice;
    private Integer productStock;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<Order>();
}
