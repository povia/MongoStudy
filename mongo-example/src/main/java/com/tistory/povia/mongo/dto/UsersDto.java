package com.tistory.povia.mongo.dto;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class UsersDto {
	private String name;
	private int age;
}
