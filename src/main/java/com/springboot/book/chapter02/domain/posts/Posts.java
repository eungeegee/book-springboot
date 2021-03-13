package com.springboot.book.chapter02.domain.posts;


import com.springboot.book.chapter02.domain.BaseTimeEntity; // createdDate, modifiedDate 을 상속받음
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복의 어노테이션, 클래스 내 모든 필드의 Getter 메소드를 자동 생성,, 즉 각 컬럼의 getter 가 생성!
@NoArgsConstructor // 롬복의 어노테이션, 기본생성자 자동추가 public Posts(){} 와 같은 효과
@Entity // JPA 의 어노테이션 ,
public class Posts extends BaseTimeEntity { // 클래스명은 DB 테이블로 전환
  // -> Posts => posts , SalesManager.java -> sales_manager table
  // 이 클래스는 DB 테이블과 매칭되는 클래스, -> 테이블 컬럼추가는 여기서 하겠구만!

  // -> 추가 : Entity (테이블이 되는 클래스)에서는 Setter 를 생성하지 않음!
  // -> 테이블에 로우를 추가한다면 생성자(또는 빌더 클래스(@Builder))를 통해 최종값을 채운 후 DB에 삽입
  // -> 수정할 경우 해당 이벤트에맞는 public 메서드를 호출 하여 변경

  @Id // JPA 의 어노테이션으로 해당 테이블의 PK 선언
  @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA 어노테이션 - PK 생성규칙, 자동증가옵션: GenerationType.IDENTITY
  private Long id;
  // -> pk id 의 경우 Long(mysql===bigint) 타입, Auto-increase 추천

  @Column(length = 500, nullable = false) // JPA 어노테이션, Column 은 굳이 선언하지 않아도 되지만, 설정이 필요한경우 사용!
  private String title;

  @Column(columnDefinition = "Text", nullable = false )
  private String content;

  private String author;

  @Builder // 롬복 어노테이션, 해당 클래스의 빌더 패턴 클래스를 생성. 생성자위에 어노테이팅 될 경우 생성자에 포함된 필드만 빌드
  public Posts(String title, String content, String author) {
    // id 는 빌더패턴에 포함되지 않는다
    // 프로젝트 초기 에는 테이블변경이 잦아 롬복 어노테이션이 코드변경량을 최소화 시켜주기 때문에 적극 사용됨
    this.title = title;
    this.content = content;
    this.author = author;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }
}
