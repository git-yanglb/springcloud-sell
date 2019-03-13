package com.sell.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sell.user.dataobject.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

	UserInfo findByOpenid(String openid);

}
