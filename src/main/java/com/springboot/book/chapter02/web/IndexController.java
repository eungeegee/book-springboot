package com.springboot.book.chapter02.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("/")
  public String index() {
    return "index"; // 머스태쉬가 자동 반응하기 때문에 resource/index.mustache 가 반환된다
  }

  @GetMapping("/posts/save")
  public String postsSave() {
    return "posts-save"; // 템플릿 폴더의 posts-save.mustache 를 응답함
  }
}