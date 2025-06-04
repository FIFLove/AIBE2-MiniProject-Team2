package com.example.board;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.board.repository.MemberRepository;
import com.example.board.repository.MemoryMemberRepository;
import com.example.board.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Configuration
public class SpringConfig {

    // private final DataSource dataSource;

	/*private EntityManager em;

	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}*/

	/*@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

	/*@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}*/

	/*@Bean
	public MemberRepository memberRepository() {

		// return new MemoryMemberRepository();
		// return new JdbcMemberRepository(dataSource);
		// return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}*/
}
