/**
 *
 */
package wang.yongrui.checklist.wechat.entity.jpa;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.basic.ChecklistItemBasic;

/**
 * @author Ryan Wang
 *
 */
@Entity(name = "CHECKLIST_ITEM")
@Getter
@Setter
public class ChecklistItemEntity extends ChecklistItemBasic {

}
