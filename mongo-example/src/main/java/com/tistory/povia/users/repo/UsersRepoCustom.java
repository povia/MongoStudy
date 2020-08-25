package com.tistory.povia.users.repo;

public interface UsersRepoCustom {
	long updateUsers(String last_name,String city);

	long custDelete(String id);
}
