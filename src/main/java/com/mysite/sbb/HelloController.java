package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
// 헬로컨트롤러라는 클래스가 컨트롤러 역할을 할거야	

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello World ~~";

	}

}
