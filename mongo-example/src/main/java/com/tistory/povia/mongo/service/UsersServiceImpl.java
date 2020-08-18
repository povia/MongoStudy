package com.tistory.povia.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.povia.mongo.doc.UsersDoc;
import com.tistory.povia.mongo.repo.UsersRepo;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepo repo;
	
	@Override
	public List<UsersDoc> findAll() {
		return repo.findAll();
	}

	@Override
	public List<UsersDoc> findByName(String name) {
		return repo.findByLastName(name);
	}

	@Override
	public UsersDoc insertData(String last_name, int age, String city) {
		UsersDoc doc = new UsersDoc();
		doc.setLast_name(last_name);
		doc.setAge(age);
		doc.setCity(city);
		return repo.insert(doc);
	}

	@Override
	public List<UsersDoc> findByRegExName(String name) {
		// TODO Auto-generated method stub
		return repo.findByRegExName(name);
	}
	
	@Override
	public long update(String last_name, String city) {
		return repo.updateUsers(last_name, city);
	}

	@Override
	public long delete(String id) {
		return repo.custDelete(id);
	}

//	@Override
//	public UsersDoc findById(String id) {
//		// TODO Auto-generated method stub
//		return repo.findById(id);
//	}

}
