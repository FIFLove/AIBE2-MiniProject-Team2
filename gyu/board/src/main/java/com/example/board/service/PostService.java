package com.example.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.board.domain.Post;
import com.example.board.repository.PostRepository;

//@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long save(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findOne(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}