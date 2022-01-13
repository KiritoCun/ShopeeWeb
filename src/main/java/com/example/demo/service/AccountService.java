package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public List<Account> getAllAccount (){
        return (List<Account>) accountRepository.findAll();
    }
	
	public Account checkLogin(String userlogin, String passlogin) {
		List<Account> accs =  accountRepository.findByUsernameAndPassword(userlogin,passlogin);
		if(accs.size()!=0) {
			return accountRepository.findByUsernameAndPassword(userlogin,passlogin).get(0);
		}
		return null;
	}
	
	public List<Account> checkRegister(String userlogin) {
		return  accountRepository.findByUsername(userlogin);
	}
	
	public void register(String userRegister, String password) {
		accountRepository.register(userRegister, password);
	}
}
