package com.mysite.sbb;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// id는 pk키로 쓸거야.. 자동으로 숫자를 넣을거야
	private Integer id; // id는 숫자지만 int를 안씀 -> 추후 null check를 할거라서 integer

	@Column(length = 200) // 200자까지 제한
	private String subject;

	@Column(columnDefinition = "TEXT") // text 길이의 제한이 없음
	private String content;

	// creatDate -> Table mapping시 creat_date로 이름이 변경
	private String cteateDate;

	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;

}
