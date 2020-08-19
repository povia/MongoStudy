package com.tistory.povia.mongo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tistory.povia.mongo.doc.UsersDoc;
import com.tistory.povia.mongo.service.UsersService;

import ch.qos.logback.classic.Logger;

@RestController
public class UsersController {

	@Autowired
	private UsersService service;

	@RequestMapping("/api/users")
	@GetMapping
	public List<UsersDoc> getUsers(){
		return service.findAll();
	}
	
//	@RequestMapping("/api/users/search")
//	@GetMapping
//	public List<UsersDoc> getUsersByName(@RequestParam(value="last_name") String name) {
//		return service.findByName(name);
//	}
	
	@RequestMapping("/api/users/search")
	@GetMapping
	public List<UsersDoc> search(@RequestParam(value = "last_name", required = false, defaultValue="") String name,
			@RequestParam(value = "from", required = false, defaultValue="0") int from,
			@RequestParam(value = "to", required = false, defaultValue="0") int to) {
		
		return service.search(name,from,to);
	}
	
	@RequestMapping("/api/users/search/reg")
	@GetMapping
	public List<UsersDoc> getUsersByRegExName(@RequestParam("last_name") String name){
		return service.findByRegExName(name);
	}
	
	@RequestMapping("/api/users/add")
	@PostMapping
	public UsersDoc insert(@RequestParam("last_name") String last_name, @RequestParam("age") int age, @RequestParam("city") String city) {
		return service.insertData(last_name, age, city);
	}
	
	@RequestMapping("/api/users/update")
	@PostMapping
	public long update(@RequestParam("last_name") String last_name, @RequestParam("city") String city) {
		return service.update(last_name, city);
	}
	
	@RequestMapping("/api/users/delete")
	public long delete(@RequestParam("id") String id) {
		return service.delete(id);
	}
}
