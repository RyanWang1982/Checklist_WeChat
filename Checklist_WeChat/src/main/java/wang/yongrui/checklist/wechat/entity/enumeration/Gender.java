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
public enum Gender {

	Male("Male"), Female("Female"), Unknown("Unknown");

	private String description;

	/**
	 * @param description
	 */
	private Gender(String description) {
		this.description = description;
	}

}
