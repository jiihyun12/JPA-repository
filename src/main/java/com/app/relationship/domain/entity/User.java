package com.app.relationship.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TBL_USER")
public class User {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String userEmail;
    private String userPassword;
    private String userName;
    private Integer userAge;


}


