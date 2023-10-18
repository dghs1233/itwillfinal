package com.spring.boot.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.boot.dao.UserRepository;
import com.spring.boot.dto.PrincipalDetails;
import com.spring.boot.model.SiteUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalService implements UserDetailsService{

	private final UserRepository userRepository;
	
	//시큐리티 session => Authentication => UserDetails
    // 여기서 리턴 된 값이 Authentication 안에 들어간다.(리턴될때 들어간다.)
    // 그리고 시큐리티 session 안에 Authentication 이 들어간다.
    //함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<SiteUser> findUser = userRepository.findByUserName(userName);
        
		if(findUser.isPresent()) {
        	
        	SiteUser siteUser = findUser.get();
        	System.out.println(siteUser);
            return new PrincipalDetails(siteUser);
        }

		return null;
	}

}