package com.example.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.domain.Member;

// select m from Member m where m.name = ?
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
