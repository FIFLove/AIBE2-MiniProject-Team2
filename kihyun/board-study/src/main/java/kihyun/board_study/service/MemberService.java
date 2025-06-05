package kihyun.board_study.service;

import kihyun.board_study.domain.Member;
import kihyun.board_study.repository.MemberRepository;
import kihyun.board_study.repository.MemoryMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMember() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findOne(String name) {
        System.out.println("[DEBUG] findOne(name) 호출됨. name = " + name);
        return memberRepository.findByName(name);
    }

    /**
     * 아이디 비밀번호 일치 조회
     */

    public Member validateMemberOrThrow(Long memberId, String password) {
        return memberRepository.findById(memberId)
                .filter(member -> member.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("ID 또는 비밀번호가 일치하지 않습니다."));
    }
}
