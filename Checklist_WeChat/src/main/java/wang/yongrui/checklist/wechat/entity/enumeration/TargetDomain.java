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

	All("All"), User("User"), Role("Role"), Privilege("Privilege");

	private String description;

	/**
	 * @param description
	 */
	private TargetDomain(String description) {
		this.description = description;
	}

}
