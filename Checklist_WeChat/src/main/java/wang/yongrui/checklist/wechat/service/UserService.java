/**
 * 
 */
package wang.yongrui.checklist.wechat.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import wang.yongrui.checklist.wechat.entity.web.User;

/**
 * @author Ryan Wang
 *
 */
@Service
public interface UserService extends UserDetailsService {

	/**
	 * @param user
	 * @return
	 */
	public User create(User user);

	/**
	 * @param weChatUnionId
	 * @return
	 */
	public User retrieveOneByWeChatUnionId(String weChatUnionId);

	/**
	 * @param loginName
	 * @return
	 */
	public User retrieveOneByLoginName(String loginName);

	/**
	 * @param user
	 * @return
	 */
	public User putUpdate(User user);

	/**
	 * @param user
	 * @return
	 */
	public User patchUpdate(User user);

	/**
	 * @param user
	 * @return
	 */
	public User delete(User user);

	/**
	 * @param weChatUnionId
	 * @return
	 */
	public User authenticateByWeChatUnionId(String weChatUnionId);

}
