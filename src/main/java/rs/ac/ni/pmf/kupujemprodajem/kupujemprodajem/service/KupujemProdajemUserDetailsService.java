package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.UserEntity;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class KupujemProdajemUserDetailsService implements UserDetailsService {

	private static final String ROLE_PREFIX = "ROLE_";

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		final UserEntity userEntity = userRepository.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
		System.out.println("zitomir stigo ovde OVDE"+userEntity.getUserName() + " " + userEntity.getPassword());
		return new UserDetailsImpl(userEntity);
	}

	@RequiredArgsConstructor
	private static final class UserDetailsImpl implements UserDetails {

		private static final long serialVersionUID = 1L;
		private final UserEntity user;
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX + "User"));
		}

		@Override
		public String getPassword() {
			return user.getPassword();
		}

		@Override
		public String getUsername() {
			return user.getUserName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	
	}
	
	
}
