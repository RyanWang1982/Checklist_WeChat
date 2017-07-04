/**
 * 
 */
package wang.yongrui.checklist.wechat.entity.web;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.basic.UserBasic;
import wang.yongrui.checklist.wechat.entity.jpa.RoleEntity;
import wang.yongrui.checklist.wechat.entity.jpa.UserEntity;

/**
 * @author Ryan Wang
 *
 */
public class User extends UserBasic implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6176438686196620165L;

	@Getter
	@Setter
	private Set<Role> roleSet;

	/**
	 * 
	 */
	public User() {
		super();
	}

	/**
	 * 
	 */
	public User(UserEntity userEntity) {
		super();
		if (null != userEntity) {
			BeanUtils.copyProperties(userEntity, this);
			if (CollectionUtils.isNotEmpty(userEntity.getRoleEntitySet())) {
				Set<Role> roleSet = new LinkedHashSet<Role>();
				for (RoleEntity roleEntity : userEntity.getRoleEntitySet()) {
					roleSet.add(new Role(roleEntity));
				}
				setRoleSet(roleSet);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getAuthorities(
	 * )
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoleSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.getLoginName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetails#
	 * isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

}
