package com.tistory.povia.commerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tistory.povia.commerce.doc.ProductDoc;
import com.tistory.povia.commerce.repo.ProductRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

	private final ProductRepo prodRepo;
	@Override
	public List<ProductDoc> findAll() {
		// TODO Auto-generated method stub
		return prodRepo.findAll();
	}

}
