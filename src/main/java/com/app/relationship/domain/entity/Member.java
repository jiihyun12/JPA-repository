package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_MEMBER")
@Getter @Setter @ToString
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@SequenceGenerator(
        name = "SEQ_MEMBER_GENERATOR" ,
        sequenceName = "SEQ_MEMBER"
)
public class Member {

    @GeneratedValue(
            generator = "SEQ_MEMBER_GENERATOR" ,
            strategy = GenerationType.SEQUENCE
    )

    @Id
    @EqualsAndHashCode.Include
    private Long id;
    private String memberName;
    private String memberAddress;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<Order>();

}
