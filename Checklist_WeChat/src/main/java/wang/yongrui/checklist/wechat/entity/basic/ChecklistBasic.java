/**
 *
 */
package wang.yongrui.checklist.wechat.entity.basic;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Ryan Wang
 *
 */
@MappedSuperclass
@Getter
@Setter
public class ChecklistBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Calendar preparationStartDateTime;

	@JsonFormat(pattern = "yyyy-MM-dd hh:mm")
	private Calendar preparationDueDateTime;

	private String notificationChronograph;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ChecklistBasic other = (ChecklistBasic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
