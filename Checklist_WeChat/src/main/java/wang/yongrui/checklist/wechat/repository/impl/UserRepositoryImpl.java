/**
 * 
 */
package wang.yongrui.checklist.wechat.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import wang.yongrui.checklist.wechat.entity.jpa.RoleEntity_;
import wang.yongrui.checklist.wechat.entity.jpa.UserEntity;
import wang.yongrui.checklist.wechat.entity.jpa.UserEntity_;
import wang.yongrui.checklist.wechat.repository.UserRepository;

/**
 * @author Ryan Wang
 *
 */
@Repository
public class UserRepositoryImpl extends SimpleJpaRepository<UserEntity, String> implements UserRepository {

	/**
	 * @param em
	 */
	@Autowired
	public UserRepositoryImpl(EntityManager em) {
		super(UserEntity.class, em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.checklist.wechat.repository.UserRepository#
	 * findOneByWeChatOAOpenId(java.lang.String)
	 */
	@Override
	public UserEntity findOneByWeChatOAOpenId(String weChatOAOpenId) {
		return super.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(UserEntity_.roleEntitySet, JoinType.LEFT).fetch(RoleEntity_.privilegeEntitySet, JoinType.LEFT);
			return criteriaBuilder.and(criteriaBuilder.equal(root.get(UserEntity_.weChatOAOpenId), weChatOAOpenId));
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.checklist.wechat.repository.UserRepository#
	 * findOneByLoginName()
	 */
	@Override
	public UserEntity findOneByLoginName(String loginName) {
		return super.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(UserEntity_.roleEntitySet, JoinType.LEFT).fetch(RoleEntity_.privilegeEntitySet, JoinType.LEFT);
			return criteriaBuilder.and(criteriaBuilder.equal(root.get(UserEntity_.loginName), loginName));
		});
	}

}
