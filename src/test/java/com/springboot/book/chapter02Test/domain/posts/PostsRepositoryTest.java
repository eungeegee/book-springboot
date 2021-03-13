package com.springboot.book.chapter02Test.domain.posts;


import com.springboot.book.chapter02.domain.posts.Posts;
import com.springboot.book.chapter02.domain.posts.PostsRepository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

  @Autowired
  PostsRepository postsRepository;

  @After // Junit 에서 단위 테스트가 끝날때마다 수행되는 메소드를 지정,
  public void cleanup() {
    postsRepository.deleteAll();
  }

  @Test
  public void importPostsList() {
    // given
    String title = "테스트 게시글";
    String content = "테스트 본문";

    postsRepository.save(Posts.builder() // 테이블 posts 에 insert/update 쿼리를 실행, id 가 있으면 update, 없으면 insert
                              .title(title)
                              .content(content)
                              .author("eungeegee@gmail.com")
                              .build());

    // when
    List<Posts> postsList = postsRepository.findAll(); // 테이블 posts 에 있는 모든 데이터를 조회해오는 메소드

    // then
    Posts posts = postsList.get(0);
    assertThat(posts.getTitle()).isEqualTo(title);
    assertThat(posts.getContent()).isEqualTo(content);
  }
}