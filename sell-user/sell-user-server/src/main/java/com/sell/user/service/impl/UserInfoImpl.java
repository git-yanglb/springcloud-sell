package com.sell.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sell.user.dataobject.UserInfo;
import com.sell.user.repository.UserInfoRepository;
import com.sell.user.service.UserService;

@Service
public class UserInfoImpl implements UserService {

	@Autowired
	private UserInfoRepository repository;

	@Override
	public UserInfo findByOpenid(String openid) {
		return repository.findByOpenid(openid);
	}

}
