package com.app.relationship.domain.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class OrderIdTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void productInsertTest(){
        Member member = new Member();
        member.setMemberName("홍길이");
        member.setMemberAddress("서울시 강남구 역삼동");
        entityManager.persist(member);

        Product product = new Product();
        product.setProductName("아이폰");
        product.setProductPrice(10_000_000);
        product.setProductStock(100);
        product.setMember(member);
        entityManager.persist(product);

        Order order = new Order();
        order.setMember(member);
        order.setProduct(product);
    }

    @Test
    public void orderFindTest(){
        String query = "SELECT m FROM Member m JOIN m.orders o WHERE m.id = :id";
        List<Member> member = entityManager.createQuery(query, Member.class).setParameter("id", 1).getResultList();
        log.info(member.toString());

        // 그래프 탐색
        member.stream().flatMap((m)-> m.getOrders().stream()).map(Order::getProduct).toList().forEach(System.out::println);
    }

}