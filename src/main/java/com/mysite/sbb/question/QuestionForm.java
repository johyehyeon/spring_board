package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

	// 기본적으로 필드단위로 된다.

	@NotEmpty(message = "제목은 필수항목 입니다.")
	@Size(max = 200)
	private String subject;

	@NotEmpty(message = "내용을 입력해주세요.")
	private String content;

}
