package com.tistory.povia.commerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.povia.commerce.doc.ProductDoc;
import com.tistory.povia.commerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/commerce/")
@RequiredArgsConstructor
public class CommerceController {
	
	private final ProductService productService;
	
	@GetMapping
	public List<ProductDoc> getProducts(){
		return productService.findAll();
	}
}
