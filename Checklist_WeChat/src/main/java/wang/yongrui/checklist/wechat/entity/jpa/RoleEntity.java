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
import wang.yongrui.checklist.wechat.entity.basic.RoleBasic;

/**
 * @author Ryan Wang
 *
 */
@Entity(name = "ROLE")
@Getter
@Setter
public class RoleEntity extends RoleBasic {

	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "ROLE_PRIVILEGE", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "privilege_id") })
	private Set<PrivilegeEntity> privilegeEntitySet;

}
