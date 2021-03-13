package com.springboot.book.chapter02Test.web;

import com.springboot.book.chapter02.web.HelloController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트 진행할때 SpringRunner 란 실행자를 함께 실행
@RunWith(SpringRunner.class) // 1: 테스트를 진행할 때 JUnit 에 내장/기타 실행자 실행
@WebMvcTest // 2 : 스프링 테스트 어노테이션중 web 에 집중된 어노테이션
public class HelloControllerTest {

  @Autowired // 스프링이 관리하는 Bean 을 주입받음.... 응?
  private MockMvc mvc; // 웹 API 를 테스트 할 때 사용, 스프링 MVC 테스트의 시작점

  @Test
  public void returnHelloString() throws Exception {
    String hello = "hello";

    // 메서드 체이닝가능
    mvc.perform(get("/hello")) // MockMvc 를 통해 /hello 주소로 HTTP GET 요청
            .andExpect(status().isOk()) // status Code 가 200인지 체크
            .andExpect(content().string(hello)); // mvc.perform 의 결과 검증, 응답 본문 내용 검증
  }

  @Test
  public void  returnHelloDto() throws Exception {
    String name = "hello";
    int amount = 1000;
    mvc.perform(get("/hello/dto")
            .param("name", name) // 요청 파라미터를 설정, 값은 String 만 허용됨
            .param("amount", String.valueOf(amount)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(name)))
            .andExpect(jsonPath("$.amount", is(amount)));
            // jsonPath : json 응답값의 필드별로 검증할 수 있는 메서드
  }
}

