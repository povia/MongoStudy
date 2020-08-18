package com.tistory.povia.mongo.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.tistory.povia.mongo.doc.UsersDoc;

import org.springframework.data.mongodb.core.query.Query;

public class UsersRepoImpl implements UsersRepoCustom{

	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public long updateUsers(String last_name, String city) {
		Query query = new Query(Criteria.where("last_name").is(last_name));
		Update update = new Update();
		update.set("city", city);
		
		
		UpdateResult result = mongoTemplate.updateFirst(query, update, UsersDoc.class);

		if(result!=null) {return result.getModifiedCount();} else {return 0;}
	}
	
	@Override
	public long custDelete(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		DeleteResult result = mongoTemplate.remove(query, UsersDoc.class);
		
		if(result!=null) {return result.getDeletedCount();}else { return 0;}
	}
}
