package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question/")
@RequiredArgsConstructor
@Controller
public class QuestionController {
	// 컨트롤러 : view 화면을 불러오는것..

// 1	private final QuestionRepository questionRepository;
	// 의존성 주입(DI) : 객체간의 의존성을 넣어준다?

	private final QuestionService questionService;

	@GetMapping("/list")
//	@ResponseBody <- 주석이유 : url mapping을 통해서 메서드를 가지고 템플릿과 연결하기때문
	public String list(Model model) {
// 1    List<Question> questionList = this.questionRepository.findAll(); // db에서 list로 가져와라
		List<Question> questionList = this.questionService.getlist();
		model.addAttribute("questionList", questionList); // db에서 읽어온 정보를 메소드로 인해 모델로 넘김

		return "question_list"; // question_list.html을 찾아서 브라우져 랜더링

	}

//  @GetMapping(value = "/question/detail/{id}") -> URL 프리픽스로 주석후 수정 클래스위에 @RequestMapping
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";

	}

}
