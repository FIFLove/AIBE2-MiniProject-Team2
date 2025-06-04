package kihyun.board_study.repository;

import kihyun.board_study.domain.Article;
import kihyun.board_study.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryArticleRepositoryTest {

    MemoryArticleRepository repository = new MemoryArticleRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void 글_작성() {

        // given
        Article article = new Article();
        article.setTitle("article title");
        article.setContent("article content");
        article.setMemberId(1L);

        // when
        repository.save(article);

        // then
        Article result = repository.findById(article.getId()).get();
        Assertions.assertThat(result).isEqualTo(article);
    }

    @Test
    public void 글_수정() {

        // given
        Article article1 = new Article();
        article1.setTitle("article1 title");
        article1.setContent("article1 content");
        article1.setMemberId(1L);
        repository.save(article1);

        // when
        Article article2 = new Article();
        article2.setTitle("updated title");
        article2.setContent("updated content");
        article2.setMemberId(1L);

        repository.update(article1.getId(), article2);

        // then
        Article result = repository.findById(article1.getId()).orElseThrow();
        Assertions.assertThat(result.getTitle()).isEqualTo("updated title");
        Assertions.assertThat(result.getContent()).isEqualTo("updated content");
        Assertions.assertThat(result.getMemberId()).isEqualTo(1L);
    }

    @Test
    public void 글_삭제() {

        // given
        Article article = new Article();
        article.setTitle("article title");
        article.setContent("article content");
        article.setMemberId(1L);
        repository.save(article);

        // when
        repository.delete(article.getId());

        // then
        Optional<Article> result = repository.findById(article.getId());
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    public void 모든_글_찾기() {

        // given
        Article article1 = new Article();
        article1.setTitle("article1 title");
        article1.setContent("article1 content");
        article1.setMemberId(1L);
        repository.save(article1);

        Article article2 = new Article();
        article2.setTitle("updated title");
        article2.setContent("updated content");
        article2.setMemberId(1L);
        repository.save(article2);

        Article article3 = new Article();
        article3.setTitle("article3 title");
        article3.setContent("article3 content");
        article3.setMemberId(2L);
        repository.save(article3);

        // when
        List<Article> articles = repository.findAll();

        // then
        Assertions.assertThat(articles.size()).isEqualTo(3);
    }
}