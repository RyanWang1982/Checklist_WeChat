/**
 * 
 */
package wang.yongrui.checklist.wechat.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.service.UserService;

/**
 * @author Ryan Wang
 *
 */
@Component
public class WeChatAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#additionalAuthenticationChecks(
	 * org.springframework.security.core.userdetails.UserDetails,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		String wechatUnionId = authentication.getPrincipal().toString();

		User weChatUser = (User) userDetails;
		if (!weChatUser.getWeChatUnionId().equals(wechatUnionId)) {
			logger.debug("Authentication failed: WeChat UnionId does not match stored value");

			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}

	protected void doAfterPropertiesSet() throws Exception {
		Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.authentication.dao.
	 * AbstractUserDetailsAuthenticationProvider#retrieveUser(java.lang.String,
	 * org.springframework.security.authentication.
	 * UsernamePasswordAuthenticationToken)
	 */
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		User loadedUser;

		try {
			loadedUser = ((UserService) this.getUserDetailsService())
					.retrieveOneByWeChatUnionId(authentication.getPrincipal().toString());
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}

		return loadedUser;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

}
