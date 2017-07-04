/**
 *
 */
package wang.yongrui.checklist.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.basic.UserBasic;

/**
 * @author Ryan Wang
 *
 */
@Entity(name = "USER")
@Getter
@Setter
public class UserEntity extends UserBasic {

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "role_id") })
	private Set<RoleEntity> roleEntitySet;

}
