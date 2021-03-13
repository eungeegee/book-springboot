package com.springboot.book.chapter02.web.dto;


import com.springboot.book.chapter02.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
  private final Long id;
  private final String title;
  private final String content;
  private final String author;

  public PostsResponseDto(Posts entity) {
    this.id = entity.getId();
    this.title = entity.getTitle();
    this.content = entity.getContent();
    this.author = entity.getAuthor();
  }
}
