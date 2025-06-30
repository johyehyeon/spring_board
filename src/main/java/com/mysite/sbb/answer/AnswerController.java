package com.mysite.sbb.answer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	// DI(객체주입, 아래 2개 객체주입)
	private final QuestionService questionService;
	private final AnswerService answerService;

	@PostMapping("/create/{id}")
	public String createAnswer(Model model,  @PathVariable("id") Integer id, @RequestParam("content") String content) {
		Question question = this.questionService.getQuestion(id);
		// todo : 답변을 저장
		this.answerService.create(question, content);
		return String.format("redirect:/question/detail/%s", id);

	}
}
