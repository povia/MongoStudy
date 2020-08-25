package com.tistory.povia.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.povia.users.doc.UsersDoc;
import com.tistory.povia.users.repo.UsersRepo;

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
	
	public List<UsersDoc> search(String name, int from, int to){
		if(!name.equals("")) {
			if(from==0 && to==0) {
				return findByName(name);
			} else { 
				if(to==0) {
					return repo.findByNameAndAgeFrom(name,from);
				}else if(from==0) {
					return repo.findByNameAndAgeTo(name, to);
				}else {
					return repo.search(name, from, to);
				}
			}
		} else {
			if(from==0 && to==0) {
				return null;
			} else { 
				if(to==0) {
					return repo.findByAgeFrom(from);
				}else if(from==0) {
					return repo.findByAgeTo(to);
				}else {
					return findByAgeBetween(from, to);
				}
			}
		}
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

	@Override
	public List<UsersDoc> findByAgeBetween(int from, int to) {
		return repo.findByAgeBetween(from, to);
	}

//	@Override
//	public UsersDoc findById(String id) {
//		// TODO Auto-generated method stub
//		return repo.findById(id);
//	}

}
