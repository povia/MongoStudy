package com.tistory.povia.mongo.doc;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "users")
public class UsersDoc {
	private String _id;
	private String username;
	private String country;
	private Object favorites;
}
