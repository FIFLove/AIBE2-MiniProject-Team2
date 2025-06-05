package kihyun.board_study.service;

import kihyun.board_study.domain.Article;
import kihyun.board_study.domain.Member;
import kihyun.board_study.repository.ArticleRepository;
import kihyun.board_study.repository.MemoryArticleRepository;
import kihyun.board_study.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    ArticleService articleService;
    MemoryArticleRepository articleRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        articleRepository = new MemoryArticleRepository();
        articleService = new ArticleService(articleRepository, memberService);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
        articleRepository.clearStore();
    }

    @Test
    void createArticle() {

        // given
        Member member = new Member();
        member.setName("member");
        member.setPassword("password");
        memberService.join(member);

        // when
        Article article = new Article();
        article.setTitle("Title");
        article.setContent("Content");
        article.setMemberId(memberService.findOne("member").get().getId());
        articleService.createArticle(article, "password");

        // then
        Article createdArticle = articleRepository.findById(article.getId()).get();
        assertEquals(article.getTitle(), createdArticle.getTitle());
    }

    @Test
    void updateArticle() {

        // given
        Member member = new Member();
        member.setName("member");
        member.setPassword("password");
        memberService.join(member);

        Article original = new Article();
        original.setTitle("Original Title");
        original.setContent("Original Content");
        original.setMemberId(member.getId());
        articleService.createArticle(original, "password");

        // when
        Article updated = new Article();
        updated.setTitle("Updated Title");
        updated.setContent("Updated Content");
        updated.setMemberId(member.getId());
        articleService.updateArticle(original.getId(), updated, "password");

        // then
        Article updatedArticle = articleRepository.findById(original.getId()).get();
        assertEquals(updated.getTitle(), updatedArticle.getTitle());
    }

    @Test
    void deleteArticle() {

        // given
        Member member = new Member();
        member.setName("member");
        member.setPassword("password");
        memberService.join(member);

        Article article = new Article();
        article.setTitle("Original Title");
        article.setContent("Original Content");
        article.setMemberId(member.getId());
        articleService.createArticle(article, "password");

        // when
        articleService.deleteArticle(article.getId(), "password");

        // then
        Optional<Article> result = articleRepository.findById(article.getId());
        Assertions.assertThat(result).isEmpty();
    }

    @Test
    void findAllArticles() {

        // given
        Member member1 = new Member();
        member1.setName("member1");
        member1.setPassword("password");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("member2");
        member2.setPassword("password");
        memberService.join(member2);

        Article article1 = new Article();
        article1.setTitle("Article 1");
        article1.setContent("Content 1");
        article1.setMemberId(member1.getId());
        articleService.createArticle(article1, "password");

        Article article2 = new Article();
        article2.setTitle("Article 2");
        article2.setContent("Content 2");
        article2.setMemberId(member2.getId());
        articleService.createArticle(article2, "password");

        // when
        List<Article> articles = articleService.findAllArticles();

        // then
        Assertions.assertThat(articles).hasSize(2);
    }

    @Test
    void findMemberArticle() {
        Member member1 = new Member();
        member1.setName("member1");
        member1.setPassword("password");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("member2");
        member2.setPassword("password");
        memberService.join(member2);

        Article article1 = new Article();
        article1.setTitle("Article 1");
        article1.setContent("Content 1");
        article1.setMemberId(member1.getId());
        articleService.createArticle(article1, "password");

        Article article2 = new Article();
        article2.setTitle("Article 2");
        article2.setContent("Content 2");
        article2.setMemberId(member2.getId());
        articleService.createArticle(article2, "password");

        Article article3 = new Article();
        article3.setTitle("Article 3");
        article3.setContent("Content 3");
        article3.setMemberId(member1.getId());
        articleService.createArticle(article3, "password");

        // when
        List<Article> articles = articleService.findMemberArticle(member1.getId());

        // then
        Assertions.assertThat(articles).hasSize(2);
    }
}