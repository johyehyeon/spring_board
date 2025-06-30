package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository qeQuestionRepository;

	public List<Question> getlist() {
		return this.qeQuestionRepository.findAll();
	}

	public Question getQuestion(Integer id) {
		// 브라우저에 없는 id값을 입력할경우 예외처리
		// Optional : null 처리를 안전하게 할수있도록 도와주는 class
		Optional<Question> question = this.qeQuestionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DatanotFoundException("qusetion not found");
		}
	}

	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		this.qeQuestionRepository.save(q);
	}

}
