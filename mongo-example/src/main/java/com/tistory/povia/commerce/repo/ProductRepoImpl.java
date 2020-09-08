package com.tistory.povia.commerce.repo;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.tistory.povia.commerce.doc.ProductDoc;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductRepoImpl implements ProductRepoCustom{
	
	private final MongoTemplate mongo;

}
