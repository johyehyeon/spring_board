package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// <> 제너릭 엔티티 연결할 클래스는 Question, 
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	Question findBySubject(String subject);

	Question findBySubjectAndContent(String subject, String content);

	List<Question> findBySubjectLike(String subject);

}
