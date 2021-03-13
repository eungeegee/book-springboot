package com.springboot.book.chapter02.service;

import com.springboot.book.chapter02.domain.posts.Posts;
import com.springboot.book.chapter02.domain.posts.PostsRepository;
import com.springboot.book.chapter02.web.dto.PostsResponseDto;
import com.springboot.book.chapter02.web.dto.PostsSaveRequestDto;
import com.springboot.book.chapter02.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;

@RequiredArgsConstructor
@Service
public class PostsService {
  private  final PostsRepository postsRepository;

  @Transactional
  public Long save(PostsSaveRequestDto requestDto) {
    return postsRepository.save(requestDto.toEntity()).getId();
  }

  @Transactional
  public Long update(Long id, PostsUpdateRequestDto requestDto) {
    Posts posts = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

    posts.update(requestDto.getTitle(), requestDto.getContent());

    return id;
  }

  @Transactional(readOnly = true)
  public PostsResponseDto findById(Long id) {
    Posts entity = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
    return new PostsResponseDto(entity);
  }
}
