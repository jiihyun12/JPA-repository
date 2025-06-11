package com.app.relationship.domain.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Commit
class EntityTests {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void setBoardTests(){
        User user = new User();
        user.setUserEmail("ggd1234@gmail.com");
        user.setUserPassword("1234");
        user.setUserName("고길동");
        user.setUserAge(17);

        Board board1 = new Board();
        board1.setBoardTitle("Hello, JPA!");
        board1.setBoardContent("JPA 1:N 관계 맺기");
        board1.setUser(user);

        Board board2 = new Board();
        board2.setBoardTitle("오점뭐");
        board2.setBoardContent("떡볶이 ㄱㄱ");
        board2.setUser(user);

        entityManager.persist(user);
        entityManager.persist(board1);
        entityManager.persist(board2);
    }

    // 그래프 탐색
    // 게시글을 통해 유저 정보를 조회

    @Test
    public void findBoardTest(){
        Board board = entityManager.find(Board.class, 152L);
        log.info(board.toString());

        // 객체 그래프 탐색
        log.info("게시글 작성자의 이름:{}", board.getUser().getUserName());
    }

    // 1번 회원이 작성한 게시글 전체를 조회
    @Test
    public void findTest2(){
//        ON절 제한
       List<Board> foundBoardList = entityManager.createQuery(
                "SELECT b FROM b JOIN b.user u WHERE u.userName = :userName", Board.class
        ).setParameter("userName", "고길동").getResultList();

       foundBoardList.stream().map(Board::toString).forEach(System.out::println);
    }

    // 작성자 변경

    @Test
    public void updateTest(){
        User newUSer = new User();
        newUSer.setUserEmail("pgd1234@gmail.com");
        newUSer.setUserPassword("1234");
        newUSer.setUserName("박길동");
        newUSer.setUserAge(22);

        entityManager.persist(newUSer);

        Board foundBoard = entityManager.find(Board.class, 252L);
        foundBoard.setUser(newUSer);
    }

    // 새로운 유저가 작성한 게시글을 조회하기
    @Test
    public void findBoardTest2(){
        List<Board> foundBoardList = entityManager.createQuery(
                "SELECT u FROM Board u JOIN u.user u WHERE u.userName = :userName"
        ).setParameter("userName", "고길동").getResultList();

        foundBoardList.stream().map(Board::toString).forEach(System.out::println);
    }

    // 삭제
    @Test
    public void deleteTest(){

        User user = entityManager.find(User.class, 152L);

        String query = "DELETE b FROM Board b WHERE b.user.id = :userId";
        Board board = entityManager.createQuery(query, Board.class).setParameter("userId", 152L).getSingleResult();
        log.info(board.toString());

        // 관계 해제
        board.setUser(null);

        // 삭제
        entityManager.remove(user);
    }

}