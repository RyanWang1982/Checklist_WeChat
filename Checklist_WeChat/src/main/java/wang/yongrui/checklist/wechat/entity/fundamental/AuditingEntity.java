/**
 *
 */
package wang.yongrui.checklist.wechat.entity.fundamental;

import java.util.Calendar;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Calendar createdDate;

	@LastModifiedDate
	@JsonIgnore
	private Calendar lastModifiedDate;

}
