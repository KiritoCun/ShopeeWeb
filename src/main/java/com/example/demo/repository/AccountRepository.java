package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	List<Account> findByUsernameAndPassword(String username,String password);
	
	List<Account> findByUsername(String username);
	
	@Modifying
	@Query(value="insert into ACCOUNT(cmnd,user_name,password) values('143894739',?1,?2)",nativeQuery = true)
	@Transactional
	void register(String username, String password);
	
}
