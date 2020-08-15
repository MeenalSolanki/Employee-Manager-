package com.mindBrowser.manageremployee.service;

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
					.orElseThrow((() -> new UsernameNotFoundException("Manger Found with this Email: " + email)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UserDetailsImpl.build(manager);
	}
}
