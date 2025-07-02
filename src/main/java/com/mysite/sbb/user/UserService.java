package com.mysite.sbb.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String nusername, String email, String password) {

		SiteUser user = new SiteUser();
		user.setUsername(nusername);
		user.setEmail(email);
		// Security에 bean으로 객체를 만들어서 주입했기때문에 객체생성 안해도됨
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		user.setPassword(passwordEncoder.encode(password));
		this.userRepository.save(user);
		return user;
	}

}
