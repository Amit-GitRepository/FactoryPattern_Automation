package com.xassure.api.tests;

import org.testng.annotations.Test;

import com.xassure.api.models.users.UsersParent;
import com.xassure.api.services.UsersService;

public class UsersApiTest {
	
	@Test
	public void testSchema() {
	
	UsersService users = new UsersService();
	UsersParent userData = users.retrieveParentAccount("https://jsonplaceholder.typicode.com/users/1");
	System.out.println(userData.getUsername());
	
	}

}
