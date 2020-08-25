package com.tistory.povia.users.service;

import java.util.List;

import com.tistory.povia.users.doc.UsersDoc;

public interface UsersService {
	public List<UsersDoc> findAll();
	public List<UsersDoc> findByName(String name);
	public List<UsersDoc> findByRegExName(String name);
	public List<UsersDoc> search(String name, int from, int to);
//	public UsersDoc findById(String id);
	public List<UsersDoc> findByAgeBetween(int from, int to);
	public UsersDoc insertData(String last_name, int age, String city);
	public long update(String last_name, String city);
	
	public long delete(String id);
	
}
