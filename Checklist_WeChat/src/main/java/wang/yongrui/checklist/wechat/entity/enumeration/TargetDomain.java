/**
 *
 */
package wang.yongrui.checklist.wechat.entity.enumeration;

import lombok.Getter;

/**
 * @author Ryan Wang
 *
 */
@Getter
public enum TargetDomain {

	User("User"), Role("Role"), Permission("Permission");

	private String description;

	/**
	 * @param description
	 */
	private TargetDomain(String description) {
		this.description = description;
	}

}
