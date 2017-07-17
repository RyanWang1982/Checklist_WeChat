/**
 * 
 */
package wang.yongrui.checklist.wechat.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.parameter.WeChatParameters;
import wang.yongrui.checklist.wechat.service.WeChatService;

/**
 * @author Ryan Wang
 *
 */
@Service
@Transactional
public class WeChatServiceImpl implements WeChatService {

	private static final String WECHAT_OAUTH_ACCESS_TOKEN_KEY = "access_token";

	private static final String WECHAT_OAUTH_REFRESH_TOKEN_KEY = "refresh_token";

	private static final String WECHAT_OPENID_KEY = "openid";

	private static final String WECHAT_UNIONID_KEY = "unionid";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeChatParameters weChatParameters;

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.checklist.wechat.service.WeChatService#
	 * authenticateViaWeChatOA(java.lang.String)
	 */
	@Override
	public User authenticateViaWeChatOA(String code) {
		User user = null;
		String userWeChatOAOpenId = getOAuthAccessToken(code).getOpenId();
		User principal = new User();
		principal.setWeChatOAOpenId(userWeChatOAOpenId);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,
				null);
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			user = (User) authentication.getPrincipal();
		} catch (AuthenticationException e) {
			e.printStackTrace();
			throw new RuntimeException("No WeChat register information found. Register your account via WeChat");
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	private WeChatOAuth getWeChatUserInfo(String code) {
		WeChatOAuth weChatOAuth = getOAuthAccessToken(code);

		String oAuthAccessTokenJsonStr = "";
		oAuthAccessTokenJsonStr = restTemplate.getForObject(
				weChatParameters.getOAuthUserInfoUrl(weChatOAuth.getAccessToken(), weChatOAuth.getOpenId()),
				oAuthAccessTokenJsonStr.getClass(), code);

		Map<String, Object> oAuthAccessTokenJsonMap = new HashMap<String, Object>();
		try {
			oAuthAccessTokenJsonMap = new ObjectMapper().readValue(oAuthAccessTokenJsonStr,
					oAuthAccessTokenJsonMap.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		weChatOAuth.setUnionId((String) oAuthAccessTokenJsonMap.get(WECHAT_UNIONID_KEY));

		return weChatOAuth;
	}

	@SuppressWarnings("unchecked")
	private WeChatOAuth getOAuthAccessToken(String code) {
		String oAuthAccessTokenJsonStr = "";
		oAuthAccessTokenJsonStr = restTemplate.getForObject(weChatParameters.getOAuthAccessTokenUrl(),
				oAuthAccessTokenJsonStr.getClass(), code);

		Map<String, Object> oAuthAccessTokenJsonMap = new HashMap<String, Object>();
		try {
			oAuthAccessTokenJsonMap = new ObjectMapper().readValue(oAuthAccessTokenJsonStr,
					oAuthAccessTokenJsonMap.getClass());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		WeChatOAuth weChatOAuth = new WeChatOAuth();
		weChatOAuth.setAccessToken((String) oAuthAccessTokenJsonMap.get(WECHAT_OAUTH_ACCESS_TOKEN_KEY));
		weChatOAuth.setRefreshToken((String) oAuthAccessTokenJsonMap.get(WECHAT_OAUTH_REFRESH_TOKEN_KEY));
		weChatOAuth.setOpenId((String) oAuthAccessTokenJsonMap.get(WECHAT_OPENID_KEY));

		return weChatOAuth;
	}

	@Getter
	@Setter
	class WeChatOAuth {

		private String openId;

		private String unionId;

		private String accessToken;

		private String refreshToken;

	}
}
