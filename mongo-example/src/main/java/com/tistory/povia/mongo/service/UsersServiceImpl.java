package com.tistory.povia.mongo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tistory.povia.mongo.doc.UsersDoc;
import com.tistory.povia.mongo.repo.UsersRepo;

public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepo repo;
	
	@Override
	public List<UsersDoc> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	public UsersDoc insert(UsersDoc doc) {
		return repo.insert(doc);
	}

}
