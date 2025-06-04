package kihyun.board_study.repository;

import kihyun.board_study.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    // 글 작성
    Article save(Article article);

    // 전체 글 목록 조회
    List<Article> findAll();

    // 글의 id로 조회 (거의 안씀)
    Optional<Article> findById(Long id);

    // 글의 작성자 id로 조회
    List<Article> findByMemberId(Long memberId);

    // 글 수정
    Optional<Article> update(Long id, Article updatedArticle);

    // 글 삭제
    Optional<Article> delete(Long id);
}
