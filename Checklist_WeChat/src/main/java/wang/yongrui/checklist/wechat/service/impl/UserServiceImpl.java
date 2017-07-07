/**
 * 
 */
package wang.yongrui.checklist.wechat.service.impl;

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

import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.repository.UserRepository;
import wang.yongrui.checklist.wechat.service.UserService;

/**
 * @author Ryan Wang
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

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
	 * @see wang.yongrui.checklist.wechat.service.UserService#
	 * authenticateByWeChatUnionId(java.lang.String)
	 */
	@Override
	public User authenticateByWeChatUnionId(String weChatUnionId) {
		User user = new User(userRepository.findOneByWeChatUnionId(weChatUnionId));
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return null;
		}

		return user;
	}

}
