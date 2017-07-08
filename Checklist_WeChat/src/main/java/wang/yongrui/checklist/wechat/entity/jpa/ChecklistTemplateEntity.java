/**
 *
 */
package wang.yongrui.checklist.wechat.entity.jpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.basic.ChecklistBasic;

/**
 * @author Ryan Wang
 *
 */
@Entity(name = "CHECKLIST_TEMPLATE")
@Getter
@Setter
public class ChecklistTemplateEntity extends ChecklistBasic {

	@ManyToOne
	private UserEntity author;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CHECKLIST_TEMPLATE_ITEM", joinColumns = {
			@JoinColumn(name = "template_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
	private Set<ChecklistItemEntity> checklistItemEntitySet;

}
