/**
 * 
 */
package wang.yongrui.checklist.wechat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import wang.yongrui.checklist.wechat.entity.jpa.UserEntity;

/**
 * @author Ryan Wang
 *
 */
@NoRepositoryBean
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

	/**
	 * @param weChatOAOpenId
	 * @return
	 */
	public UserEntity findOneByWeChatOAOpenId(String weChatOAOpenId);

	/**
	 * @param loginName
	 * @return
	 */
	public UserEntity findOneByLoginName(String loginName);

}
