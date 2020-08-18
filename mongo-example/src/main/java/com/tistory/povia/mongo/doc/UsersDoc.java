package com.tistory.povia.mongo.doc;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "users")
public class UsersDoc {
	
	private @Id String id;
	private @Field String last_name;
	private @Field int age;
	private @Field String city;
}
