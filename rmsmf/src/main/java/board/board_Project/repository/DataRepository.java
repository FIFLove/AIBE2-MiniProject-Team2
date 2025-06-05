package board.board_Project.repository;

import board.board_Project.domain.Data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//@Repository
public interface DataRepository extends JpaRepository<Data, Long>{

    Optional<Data> findByTitle(String title);    // 제목으로 게시글 조회
    List<Data> findByName(String name);          // 작성자 이름으로 게시글 목록 조회
}
