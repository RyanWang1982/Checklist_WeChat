/**
 *
 */
package wang.yongrui.checklist.wechat.entity.jpa;

import javax.persistence.Entity;

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

}
