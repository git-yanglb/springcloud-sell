package com.sell.user.service;

import com.sell.user.dataobject.UserInfo;

public interface UserService {

	UserInfo findByOpenid(String openid);

}
