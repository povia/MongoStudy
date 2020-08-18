package com.tistory.povia.mongo.service;

import java.util.List;

import com.tistory.povia.mongo.doc.UsersDoc;

public interface UsersService {
	public List<UsersDoc> findAll();
}
