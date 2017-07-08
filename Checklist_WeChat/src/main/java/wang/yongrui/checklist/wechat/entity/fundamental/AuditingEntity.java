/**
 *
 */
package wang.yongrui.checklist.wechat.entity.fundamental;

import java.util.Calendar;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ryan Wang
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AuditingEntity {

	@CreatedDate
	private Calendar createdDate;

	@CreatedBy
	private String createrWeChatUnionId;

	@LastModifiedDate
	private Calendar lastModifiedDate;

	@LastModifiedBy
	private String lastModifierWeChatUnionId;

}
