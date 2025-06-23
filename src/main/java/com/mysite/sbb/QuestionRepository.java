package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;

// <> 제너릭 엔티티 연결할 클래스는 Question, 
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
