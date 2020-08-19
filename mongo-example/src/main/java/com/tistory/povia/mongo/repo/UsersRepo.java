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
	
	@Query("{'age': { $gte: ?0, $lte: ?1} }")
	public List<UsersDoc> findByAgeBetween(int from, int to);
	
	@Query("{'age': { $gte: ?0} }")
	public List<UsersDoc> findByAgeFrom(int from);
	
	@Query("{'age': { $lte: ?0} }")
	public List<UsersDoc> findByAgeTo(int to);
	
	@Query("{'last_name': '?0','age': { $gte: ?1, $lte: ?2} }")
	public List<UsersDoc> search(String name, int from, int to);
	
	@Query("{'last_name': '?0','age': { $gte: ?1} }")
	public List<UsersDoc> findByNameAndAgeFrom(String name, int from);
	
	@Query("{'last_name': '?0','age': { $lte: ?1} }")
	public List<UsersDoc> findByNameAndAgeTo(String name, int to);
}
