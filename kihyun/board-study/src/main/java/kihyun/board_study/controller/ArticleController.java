package kihyun.board_study.controller;

import kihyun.board_study.domain.Article;
import kihyun.board_study.domain.Member;
import kihyun.board_study.service.ArticleService;
import kihyun.board_study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final MemberService memberService;

    @Autowired
    public ArticleController(ArticleService articleService, MemberService memberService) {
        this.articleService = articleService;
        this.memberService = memberService;
    }

    @GetMapping("articles/new")
    public String createForm(Model model) {
        return "articles/createArticleForm";
    }

    @PostMapping("articles/new")
    public String create(ArticleForm form) {
        Article article = new Article();
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());

        Member member = memberService.findOne(form.getMemberName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        article.setMemberId(member.getId());

        articleService.createArticle(article, form.getMemberPassword());
        return "redirect:/";
    }

    @GetMapping("articles")
    public String list(Model model) {
        List<Article> articles = articleService.findAllArticles();
        model.addAttribute("articles", articles);
        return "articles/articleList";
    }
}
