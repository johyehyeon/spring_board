package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;

//	public List<Question> getlist() {
//		return this.qeQuestionRepository.findAll();
//	}
	// data list에서 page단위로 변경.. Sort중첩클래스.. list를 정렬하는 조건을 가짐(sort)
	public Page<Question> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}

	public Question getQuestion(Integer id) {
		// 브라우저에 없는 id값을 입력할경우 예외처리
		// Optional : null 처리를 안전하게 할수있도록 도와주는 class
		Optional<Question> question = this.questionRepository.findById(id);
		if (question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("qusetion not found");
		}
	}

//	로그인한 한사람 질문저장하기
	public void create(String subject, String content, SiteUser user) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user); // 작성자 저장!
		this.questionRepository.save(q);
	}

//  질문저장하기
//	public void create(String subject, String content) {
//		Question q = new Question();
//		q.setSubject(subject);
//		q.setContent(content);
//		q.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q);
//	}

}
