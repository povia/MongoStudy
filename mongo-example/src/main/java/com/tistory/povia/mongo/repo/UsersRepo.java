package com.tistory.povia.mongo.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tistory.povia.mongo.doc.UsersDoc;

public interface UsersRepo extends MongoRepository<UsersDoc, String>{

}
