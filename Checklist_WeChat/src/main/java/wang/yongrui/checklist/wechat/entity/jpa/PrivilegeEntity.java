/**
 * 
 */
package wang.yongrui.checklist.wechat.entity.jpa;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.basic.PrivilegeBasic;

/**
 * @author Ryan Wang
 *
 */
@Entity(name = "PRIVILEGE")
@Getter
@Setter
public class PrivilegeEntity extends PrivilegeBasic {

}
