package com.sell.user.controller;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sell.common.constant.CookieConstant;
import com.sell.common.constant.RedisConstant;
import com.sell.common.enums.ResultEnum;
import com.sell.common.util.CookieUtils;
import com.sell.common.util.ResultVOUtil;
import com.sell.common.vo.ResultVO;
import com.sell.user.dataobject.UserInfo;
import com.sell.user.enums.RoleEnum;
import com.sell.user.service.UserService;

@RestController
@RequestMapping("/login")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private StringRedisTemplate redisTemplate;

	@GetMapping("/buyer")
	public ResultVO<UserInfo> buyer(@RequestParam("openid") String openid, HttpServletResponse response) {
		UserInfo userInfo = userService.findByOpenid(openid);
		if (userInfo == null) {
			return ResultEnum.FAILURE.getResult("登录失败", UserInfo.class);
		}
		if (RoleEnum.BUYER.getCode() != userInfo.getRole()) {
			return ResultEnum.FAILURE.getResult("用户角色错误", UserInfo.class);
		}

		CookieUtils.set(response, CookieConstant.OPENID, userInfo.getOpenid());

		return ResultVOUtil.success(userInfo);
	}

	@GetMapping("/seller")
	public ResultVO<UserInfo> seller(@RequestParam("openid") String openid, HttpServletRequest request,
			HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();
		String redisOpenid = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(CookieConstant.TOKEN)) {
				redisOpenid = redisTemplate.opsForValue()
						.get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue()));
			}
		}
		if (redisOpenid != null) {
			return ResultEnum.SUCCESS.getResult("登录成功", UserInfo.class);
		}

		UserInfo userInfo = userService.findByOpenid(openid);
		if (userInfo == null) {
			return ResultEnum.FAILURE.getResult("登录失败", UserInfo.class);
		}
		if (RoleEnum.SELLER.getCode() != userInfo.getRole()) {
			return ResultEnum.FAILURE.getResult("用户角色错误", UserInfo.class);
		}

		String token = UUID.randomUUID().toString();
		redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE, token), userInfo.getOpenid(),
				CookieConstant.EXPIREIN, TimeUnit.SECONDS);

		CookieUtils.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIREIN);

		return ResultVOUtil.success(userInfo);
	}

}
