/**
 * 
 */
package wang.yongrui.checklist.wechat.service;

import org.springframework.stereotype.Service;

import wang.yongrui.checklist.wechat.entity.web.User;

/**
 * @author Ryan Wang
 *
 */
@Service
public interface WeChatService {

	/**
	 * @param code
	 * @return
	 */
	public User authenticateViaWeChatOA(String code);

}
