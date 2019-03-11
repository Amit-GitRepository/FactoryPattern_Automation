package com.xassure.api.tests;

import org.testng.annotations.Test;
import com.xassure.api.models.users.UsersParent;
import com.xassure.api.services.UsersService;

public class UsersApiTest {
	
	@Test
	public void testSchema() {
	
	UsersService empService = new UsersService();
	UsersParent empData = empService.retrieveParentAccount("https://reqres.in/api/users?page=2");
	System.out.println(empData.getData().get(0).getId());
	
	}

}
