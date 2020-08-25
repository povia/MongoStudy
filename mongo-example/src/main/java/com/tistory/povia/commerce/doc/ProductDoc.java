package com.tistory.povia.commerce.doc;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "products")
public class ProductDoc {
	private String slug;
	private String sku;
	private String name;
	private String description;
}
