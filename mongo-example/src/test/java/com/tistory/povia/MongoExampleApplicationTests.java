package com.tistory.povia;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tistory.povia.mongo.doc.UsersDoc;
import com.tistory.povia.mongo.repo.UsersRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
class MongoExampleApplicationTests {
	@Autowired
	UsersRepo repo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	private void getCollectionObjects() {
		System.out.println("Test Start");
		List<UsersDoc> list = repo.findAll();
		for(UsersDoc doc : list) {
			System.out.println(doc.toString());
		}
		System.out.println("Test Ended with " + list.size() + " records");
	}
	
	@Test
	private void insertTest() {
		System.out.println("Insert Test Start");
		UsersDoc testDoc = new UsersDoc();
		testDoc.setLast_name("jones");
		testDoc.setAge(40);
		UsersDoc result =repo.insert(testDoc);
		System.out.println("Insert Test Ended with " + result.toString());
	}
}
