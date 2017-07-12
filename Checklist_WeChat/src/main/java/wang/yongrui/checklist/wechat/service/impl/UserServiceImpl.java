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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.parameter.WeChatParameters;
import wang.yongrui.checklist.wechat.repository.UserRepository;
import wang.yongrui.checklist.wechat.service.UserService;

/**
 * @author Ryan Wang
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final String WECHAT_OAUTH_ACCESS_TOKEN_KEY = "access_token";

	private static final String WECHAT_OAUTH_REFRESH_TOKEN_KEY = "refresh_token";

	private static final String WECHAT_OPENID_KEY = "openid";

	private static final String WECHAT_UNIONID_KEY = "unionid";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WeChatParameters weChatParameters;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(userRepository.findOneByLoginName(username));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.checklist.wechat.service.UserService#create(wang.yongrui.
	 * checklist.wechat.entity.web.User)
	 */
	@Override
	public User create(User user) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.checklist.wechat.service.UserService#
	 * retrieveOneByWeChatUnionId(java.lang.String)
	 */
	@Override
	public User retrieveOneByWeChatUnionId(String weChatUnionId) {
		return new User(userRepository.findOneByWeChatUnionId(weChatUnionId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.checklist.wechat.service.UserService#retrieveOneByLoginName(
	 * java.lang.String)
	 */
	@Override
	public User retrieveOneByLoginName(String loginName) {
		return new User(userRepository.findOneByLoginName(loginName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.checklist.wechat.service.UserService#putUpdate(wang.yongrui.
	 * checklist.wechat.entity.web.User)
	 */
	@Override
	public User putUpdate(User user) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.checklist.wechat.service.UserService#patchUpdate(wang.
	 * yongrui.checklist.wechat.entity.web.User)
	 */
	@Override
	public User patchUpdate(User user) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.checklist.wechat.service.UserService#delete(wang.yongrui.
	 * checklist.wechat.entity.web.User)
	 */
	@Override
	public User delete(User user) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.checklist.wechat.service.UserService#authenticateViaWeChat(
	 * java.lang.String)
	 */
	@Override
	public User authenticateViaWeChat(String code) {
		User user = null;
		String userWeChatUnionId = getWeChatUserInfo(code).getUnionId();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				userWeChatUnionId, null);
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			user = (User) authentication.getDetails();
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
