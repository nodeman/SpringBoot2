package org.example.springboot2.service;

import lombok.RequiredArgsConstructor;
import org.example.springboot2.domain.posts.Posts;
import org.example.springboot2.domain.posts.PostsRepository;
import org.example.springboot2.web.dto.PostsResponseDto;
import org.example.springboot2.web.dto.PostsSaveRequestDto;
import org.example.springboot2.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당데이터가 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당사용자가 없습니다.id=" + id));
        return new PostsResponseDto(posts);
    }
}
