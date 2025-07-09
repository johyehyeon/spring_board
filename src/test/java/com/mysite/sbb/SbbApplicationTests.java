package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionService questionService;

//	@Transactional
	@Test
	void testJpa() {
//		Question q1 = new Question();
//		q1.setSubject("sbb가 무엇인가요?알려주세요");
//		q1.setContent("좀 더 알고싶어요");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);

//		Question q2 = new Question();
//		q2.setSubject("스프링부트 모델.");
//		q2.setContent("id는 자동으로 생성되나요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);

//		// find all
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(2, all.size());
//		Question q = all.get(0);
//		assertEquals("sbb가 무엇인가요?알려주세요", q.getSubject());

		// finByID()
		// Optional<Question> op = this.questionRepository.findById(1);
		// if (op.isPresent()) {
		// Question q = op.get();
		// assertEquals("sbb가 무엇인가요?", q.getSubject());
		// }

		// findBySubject
		// Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		// assertEquals(1, q.getId());

		// findBySubjectAndContent()
//		Question q = this.questionRepository.findBySubjectAndContent("스프링부트 모델 질문입니다.", "id는 자동으로 생성되나요?");
//		assertEquals(2, q.getId());

		// findBySubjectLike()
		// List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
		// Question q = qList.get(0);

		// 질문데이터 수정하기
//		Optional<Question> op = this.questionRepository.findById(1);
//		assertTrue(op.isPresent());
		// 데이터가 존재하면
//		Question q = op.get();
//		q.setSubject("제목수정11");
//		this.questionRepository.save(q);

		// 질문데이터 삭제하기
//		assertEquals(2, this.questionRepository.count());
//		Optional<Question> op = this.questionRepository.findById(1);
//		assertTrue(op.isPresent());
//		Question q = op.get();
//		this.questionRepository.delete(q);
//		// 삭제 후 재 검증
//		assertEquals(1, this.questionRepository.count());

//		// 답변데이터 저장 1 : 질문글 가져오기
//		Optional<Question> op = this.questionRepository.findById(2);
//		assertTrue(op.isPresent());
//		Question q = op.get();
//		// 답변데이터 저장 2 : 답변데이터 저장
//		Answer a = new Answer();
//		a.setContent("답변입니다.");
//		a.setQuestion(q); // 어떤 질문의
//		// 답변인지 알기위해 Question 객체가 필요함
//		a.setCreateDate(LocalDateTime.now());
//		this.answerRepository.save(a);

//		// 답변데이터 조회하기 finByID()
//		Optional<Answer> oa = this.answerRepository.findById(1);
//		assertTrue(oa.isPresent());
//		Answer a = oa.get();
//		assertEquals(2, a.getQuestion().getId());

		// 답변데이터를 통해 질문 데이터 찾기 or 질문데이터를 통해 답변데이터 찾기
//		Optional<Question> op = this.questionRepository.findById(2);
//		assertTrue(op.isPresent());
//		Question q = op.get();
//
//		List<Answer> answerList = q.getAnswerList();
//		assertEquals(1, answerList.size());
//		assertEquals("답변입니다.", answerList.get(0).getContent());

		// 테스트 데이터 300개 생성
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트데이터입니다.:[%03d]", i);
			String content = "내용무";
			this.questionService.create(subject, content, null);
		}

	}

}
