package com.tistory.povia.mongo.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.tistory.povia.mongo.doc.UsersDoc;

public interface UsersRepo extends MongoRepository<UsersDoc, String>, UsersRepoCustom{
	
	@Query("{last_name: '?0'}")
	List<UsersDoc> findByLastName(String name);
	
	@Query("{last_name: { $regex:?0,$options:'i'} }")
	List<UsersDoc> findByRegExName(String name);

}
