/**
 *
 */
package wang.yongrui.checklist.wechat.entity.basic;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.enumeration.ActionPermission;
import wang.yongrui.checklist.wechat.entity.enumeration.TargetDomain;
import wang.yongrui.checklist.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Ryan Wang
 *
 */
@MappedSuperclass
@Getter
@Setter
public class PrivilegeBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private TargetDomain targetDomain;

	@Enumerated(value = EnumType.STRING)
	private ActionPermission permission;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((permission == null) ? 0 : permission.hashCode());
		result = prime * result + ((targetDomain == null) ? 0 : targetDomain.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrivilegeBasic other = (PrivilegeBasic) obj;
		if (permission != other.permission)
			return false;
		if (targetDomain != other.targetDomain)
			return false;
		return true;
	}

}
