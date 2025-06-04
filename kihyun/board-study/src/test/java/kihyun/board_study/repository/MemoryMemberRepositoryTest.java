package kihyun.board_study.repository;

import kihyun.board_study.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // given
        Member member = new Member();
        member.setName("member");

        // when
        repository.save(member);

        // then
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findByName() {
        // given
        Member member = new Member();
        member.setName("member");

        // when
        repository.save(member);

        // then
        Member result = repository.findByName("member").get();
        Assertions.assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("member1");
        member2.setName("member2");

        // when
        repository.save(member1);
        repository.save(member2);

        // then
        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
