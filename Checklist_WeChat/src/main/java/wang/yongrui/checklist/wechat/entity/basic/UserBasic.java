/**
 *
 */
package wang.yongrui.checklist.wechat.entity.basic;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.checklist.wechat.entity.enumeration.Gender;
import wang.yongrui.checklist.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Ryan Wang
 *
 */
@MappedSuperclass
@Getter
@Setter
public class UserBasic extends AuditingEntity {

	@Id
	@Column(unique = true)
	private String loginName;

	// @Column(unique = true)
	// @NotBlank(message = "weChatUnionId", groups = { UserCreateValidator.class
	// })
	private String weChatUnionId;

	private String weChatOAOpenId;

	private String weChatMPOpenId;

	@JsonIgnore
	private String password;

	private String cnFirstName;

	private String cnLastName;

	private String enFirstName;

	private String enLastName;

	@Column(unique = true)
	private String mobileNumber;

	@Column(unique = true)
	private String eMail;

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Gender gender;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar dateOfBirth;

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((weChatUnionId == null) ? 0 : weChatUnionId.hashCode());
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
		UserBasic other = (UserBasic) obj;
		if (weChatUnionId == null) {
			if (other.weChatUnionId != null)
				return false;
		} else if (!weChatUnionId.equals(other.weChatUnionId))
			return false;
		return true;
	}

}
