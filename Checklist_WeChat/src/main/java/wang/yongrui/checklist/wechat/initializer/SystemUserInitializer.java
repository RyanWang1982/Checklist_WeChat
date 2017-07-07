/**
 * 
 */
package wang.yongrui.checklist.wechat.initializer;

import static wang.yongrui.checklist.wechat.constant.SystemConstant.*;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wang.yongrui.checklist.wechat.entity.enumeration.ActionPermission;
import wang.yongrui.checklist.wechat.entity.enumeration.Gender;
import wang.yongrui.checklist.wechat.entity.enumeration.TargetDomain;
import wang.yongrui.checklist.wechat.entity.jpa.PrivilegeEntity;
import wang.yongrui.checklist.wechat.entity.jpa.RoleEntity;
import wang.yongrui.checklist.wechat.entity.jpa.UserEntity;
import wang.yongrui.checklist.wechat.repository.UserRepository;

/**
 * @author Ryan Wang
 *
 */
@Component
public class SystemUserInitializer {

	@Autowired
	private UserRepository userRepository;

	// @Autowired
	// private PasswordEncoder passwordEncoder;

	@PostConstruct
	public void initial() {
		PrivilegeEntity privilegeEntity = new PrivilegeEntity();
		privilegeEntity.setName(SYSTEM_ADMIN_PRIVILEGE_NAME);
		privilegeEntity.setDescription(SYSTEM_ADMIN_PRIVILEGE_DESCRIPTION);
		privilegeEntity.setTargetDomain(TargetDomain.All);
		privilegeEntity.setPermission(ActionPermission.All);

		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setName(SYSTEM_ADMIN_ROLE_NAME);
		roleEntity.setDescription(SYSTEM_ADMIN_ROLE_DESCRIPTION);

		Set<PrivilegeEntity> privilegeEntitySet = new LinkedHashSet<PrivilegeEntity>();
		privilegeEntitySet.add(privilegeEntity);
		roleEntity.setPrivilegeEntitySet(privilegeEntitySet);
		Set<RoleEntity> roleEntitySet = new LinkedHashSet<RoleEntity>();
		roleEntitySet.add(roleEntity);

		UserEntity userEntity = new UserEntity();
		userEntity.setWeChatUnionId("RyanWang12233368");
		userEntity.setLoginName("Ryan_Wang");
		userEntity.setPassword("12233368");
		userEntity.setGender(Gender.Male);
		userEntity.setRoleEntitySet(roleEntitySet);
		userRepository.saveAndFlush(userEntity);
	}

}
