package com.mysite.sbb.question;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/question/")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	// 컨트롤러 : view 화면을 불러오는것..

// 1	private final QuestionRepository questionRepository;
	// 의존성 주입(DI) : 객체간의 의존성을 넣어준다?

	private final QuestionService questionService;
	private final UserService userService;

////	@GetMapping("/question/list") -> URL 프리픽스로 주석후 수정 클래스위에 @RequestMapping
//	@GetMapping("/list")
////	@ResponseBody <- 주석이유 : url mapping을 통해서 메서드를 가지고 템플릿과 연결하기때문
////	public String list(Model model) {
////      List<Question> questionList = this.questionRepository.findAll(); // db에서 list로 가져와라
//		List<Question> questionList = this.questionService.getlist();
//		model.addAttribute("questionList", questionList); // db에서 읽어온 정보를 메소드로 인해 모델로 넘김
//		return "question_list"; // question_list.html을 찾아서 브라우져 랜더링
//	}
	// list에서 page로 바뀌면서 수정
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
		return "question_list";
	}

	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";

	}

	@PreAuthorize("isAuthenticated()") // 강제로 로그인페이지로 보냄
	@GetMapping("/create") // 질문 작성 화면 보여주기
	public String questionCreate(QuestionForm questionForm, Principal principal) {
		return "question_form";
	}

	@PostMapping("/create") // 질문 작성 처리
	public String questionCreate2(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		return "redirect:/question/list";
	}

	// 수정하기 화면 가기
	@PreAuthorize("isAuthenticated()") // 강제로 로그인페이지로 보냄
	@GetMapping("/modify/{id}")
	public String questionModify(@PathVariable("id") Integer id, QuestionForm questionForm, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		if (!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		// 수정할 데이터를 조회해서 보여주기
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		return "question_form";
	}

	// 수정내용 저장
	@PreAuthorize("isAuthenticated()") // 강제로 로그인페이지로 보냄
	@PostMapping("/modify/{id}")
	public String questionModify(@PathVariable("id") Integer id, @Valid QuestionForm questionForm, Principal principal,
			BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			return "question_form";
		}
		Question question = this.questionService.getQuestion(id);
		if (!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		return String.format("redirect:/question/detail/%s", id);

	}

	// 질문 삭제
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		if (!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		}
		this.questionService.delete(question);
		return "redirect:/";
	}

}
