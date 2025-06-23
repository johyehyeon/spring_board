package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void testJpa() {
		// Question q1 = new Question();
		// q1.setSubject("sbb가 무엇인가요?");
		// q1.setContent("좀 더 알고싶어요");
		// q1.setCteateDate(LocalDateTime.now());
		// this.questionRepository.save(q1);

		// Question q2 = new Question();
		// q2.setSubject("스프링부트 모델 질문입니다.");
		// q2.setContent("id는 자동으로 생성되나요?");
		// q2.setCteateDate(LocalDateTime.now());
		// this.questionRepository.save(q2);

		// find all
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size());

		// 인덱스 0에 있는 제목 수정
		Question q = all.get(0);
		assertEquals("sbb가 뭘까요?", q.getSubject());
	}

}
