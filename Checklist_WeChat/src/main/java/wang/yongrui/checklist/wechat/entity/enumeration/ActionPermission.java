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
public enum ActionPermission {

	All("All"), Create("Create"), Retrieve("Retrieve"), Update("Update"), Delete("Delete");

	private String description;

	/**
	 * @param description
	 */
	private ActionPermission(String description) {
		this.description = description;
	}

}
