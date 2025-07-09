package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// id는 pk키로 쓸거야.. 자동으로 숫자를 넣을거야
	private Integer id; // id는 숫자지만 int를 안씀 -> 추후 null check를 할거라서 integer

	@Column(columnDefinition = "TEXT") // text 길이의 제한이 없음
	private String content;

	private LocalDateTime createDate;

	// 중요!
	@ManyToOne
	private Question question;

	@ManyToOne
	private SiteUser author;

	private LocalDateTime modifyDate;

}
