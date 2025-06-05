package kihyun.board_study.repository;

import kihyun.board_study.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryArticleRepository implements ArticleRepository {

    private static Map<Long, Article> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Article save(Article article) {
        article.setId(sequence++);
        store.put(article.getId(), article);
        return article;
    }

    @Override
    public List<Article> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Article> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Article> findByMemberId(Long memberId) {
        return store.values().stream()
                .filter(article -> article.getMemberId().equals(memberId))
                .toList();
    }

    @Override
    public Optional<Article> update(Long id, Article updatedArticle) {
        Article original = store.get(id);
        if (original == null) return null;

        original.setTitle(updatedArticle.getTitle());
        original.setContent(updatedArticle.getContent());

        return Optional.of(original);
    }

    @Override
    public Optional<Article> delete(Long id) {
        return Optional.ofNullable(store.remove(id));
    }

    public void clearStore() {
        store.clear();
    }
}
