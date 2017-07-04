/**
 * 
 */
package wang.yongrui.checklist.wechat.entity.web;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import wang.yongrui.checklist.wechat.entity.basic.PrivilegeBasic;
import wang.yongrui.checklist.wechat.entity.jpa.PrivilegeEntity;

/**
 * @author Ryan Wang
 *
 */
public class Privilege extends PrivilegeBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1523499133646747779L;

	/**
	 * 
	 */
	public Privilege() {
		super();
	}

	/**
	 * 
	 */
	public Privilege(PrivilegeEntity privilegeEntity) {
		super();
		if (null != privilegeEntity) {
			BeanUtils.copyProperties(privilegeEntity, this);
		}
	}

}
