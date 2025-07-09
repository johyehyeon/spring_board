package com.mysite.sbb.answer;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	// DI(객체주입, 아래 2개 객체주입)
	private final QuestionService questionService;
	private final AnswerService answerService;
	// DI UserService 객체주입
	private final UserService userService;

	@PreAuthorize("isAuthenticated()") // 강제로 로그인페이지로 보냄
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm,
			BindingResult bindingResult, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		// pfincipal 객체를 통해서 사명자명을 얻고 siteUser 객체로 저장
		SiteUser siteUser = this.userService.getUser(principal.getName());
		if (bindingResult.hasErrors()) {
			model.addAttribute("question", question);
			return "question_detail";
		}
		// 답변등록 될때 siteUser(ahuhor_id, 글쓴이 ID)를 저장
		this.answerService.create(question, answerForm.getContent(), siteUser);
		return String.format("redirect:/question/detail/%s", id);

//		@PostMapping("/create/{id}")
//		public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam("content") String content) {
//			Question question = this.questionService.getQuestion(id);
//			// todo : 답변을 저장
//			this.answerService.create(question, content);
//			return String.format("redirect:/question/detail/%s", id);
		//
//		}

	}
}
