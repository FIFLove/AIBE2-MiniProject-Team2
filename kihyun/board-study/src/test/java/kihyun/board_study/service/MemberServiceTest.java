package kihyun.board_study.service;

import kihyun.board_study.domain.Member;
import kihyun.board_study.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception {

        // given
        Member member = new Member();
        member.setName("member");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals("member", findMember.getName());
    }

    @Test void 중복_회원_예외() throws Exception {

        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("member");
        member2.setName("member");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        // then
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test void 회원_이름_찾기() throws Exception {

        // given
        Member member = new Member();
        member.setName("member");
        memberService.join(member);

        // when
        Member findMember = memberService.findOne("member").get();

        // then
        assertEquals("member", findMember.getName());
    }
}