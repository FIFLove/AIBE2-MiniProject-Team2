package kihyun.board_study;

import kihyun.board_study.repository.ArticleRepository;
import kihyun.board_study.repository.MemberRepository;
import kihyun.board_study.repository.MemoryArticleRepository;
import kihyun.board_study.repository.MemoryMemberRepository;
import kihyun.board_study.service.ArticleService;
import kihyun.board_study.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoardConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository(), memberService());
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new MemoryArticleRepository();
    }
}
