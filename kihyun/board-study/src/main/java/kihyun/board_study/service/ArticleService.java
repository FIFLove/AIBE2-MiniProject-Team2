package kihyun.board_study.service;

import kihyun.board_study.domain.Article;
import kihyun.board_study.repository.ArticleRepository;

import java.util.List;
import java.util.Optional;

public class ArticleService {
    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    public ArticleService(ArticleRepository articleRepository, MemberService memberService) {
        this.articleRepository = articleRepository;
        this.memberService = memberService;
    }

    /**
     * 글 작성
     */

    public Long createArticle(Article article, String password) {
        // 검증: 실패 시 예외 발생
        memberService.validateMemberOrThrow(article.getMemberId(), password);

        // 검증 통과 후 저장
        articleRepository.save(article);
        return article.getId();
    }

    /**
     * 글 수정
     */

    public Optional<Article> updateArticle(Long articleId, Article updated, String password) {
        memberService.validateMemberOrThrow(findMemberIdByArticleId(articleId), password);
        articleRepository.update(articleId, updated);
        return Optional.of(updated);
    }

    /**
     * 글 삭제
     */

    public Optional<Article> deleteArticle(Long articleId, String password) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 존재하지 않습니다."));

        memberService.validateMemberOrThrow(article.getMemberId(), password);
        return articleRepository.delete(articleId);
    }

    /**
     * 전체 글 조회
     */

    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> findOne(Long articleId) {
        return articleRepository.findById(articleId);
    }

    public List<Article> findMemberArticle(Long memberId) {
        return articleRepository.findByMemberId(memberId);
    }

    /**
     * 글 작성자 조회
     */
    public Long findMemberIdByArticleId(Long articleId) {
        return articleRepository.findById(articleId).get().getMemberId();
    }
}
