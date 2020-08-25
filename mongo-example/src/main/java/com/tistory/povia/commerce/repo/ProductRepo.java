package com.tistory.povia.commerce.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tistory.povia.commerce.doc.ProductDoc;

public interface ProductRepo extends MongoRepository<ProductDoc, String>{
	List<ProductDoc> findAll();
	
	
}
