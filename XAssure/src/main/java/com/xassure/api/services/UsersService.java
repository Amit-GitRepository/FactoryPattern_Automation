package com.xassure.api.services;

import com.xassure.api.httpclient.GetRequest;
import com.xassure.api.models.users.UsersParent;

public class UsersService {
	
	public UsersParent retrieveParentAccount(String url) {
		return new GetRequest().GetRequestWithOauth(url)
				.as(UsersParent.class);
	}

}
