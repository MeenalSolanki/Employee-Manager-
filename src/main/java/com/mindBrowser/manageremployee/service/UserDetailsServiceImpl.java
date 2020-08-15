package com.mindBrowser.manageremployee.service;

import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindBrowser.manageremployee.entity.Manager;
import com.mindBrowser.manageremployee.repository.ManagerRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	ManagerRepository managerRepository;
	Manager manager;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try {
			manager = managerRepository.findByEmail(email)
					.orElseThrow(new Supplier<Throwable>() {
						@Override
						public Throwable get() {
							return new UsernameNotFoundException("Account Not Found with email: " );
						}
					});
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return UserDetailsImpl.build(manager);
	}
}
