package com.cos.role.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.role.model.User;
import com.cos.role.model.UserRole;

// Principal (접근 주체) = 세션처럼 사용 = Spring Security Context에 저장
public class MyUserDetails implements UserDetails{
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//계정의 비밀번호를 리턴한다.
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	//계정의 이름을 리턴한다.
	@Override
	public String getUsername() {
		return user.getUsername();
	}
	
	//계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		//아이디가 일정기간(1년?정도) 지나면 만료되게한다. 싫으면 true
		return true;
	}

	//계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		// 비번 몇번 이상 틀리면 락이 걸리게한다. 싫으면 true
		return true;
	}

	//비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// 구글에 UserDetails 해서 치면 이 함수들의 기능들이 나온다 그거보면 돼
		return true;
	}

	//계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}

	// 계정이 갖고있는 권한 목록을 리턴한다. (화)
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
//			List<UserRole> roles = user.getRoles();
//			for(UserRole ur : roles) {
//				System.out.println(ur.getRole().getRole());
//			}
			
			return user.getRoles().stream()
					.map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRole().getRole()))
					.collect(Collectors.toList());
		}
	
}
