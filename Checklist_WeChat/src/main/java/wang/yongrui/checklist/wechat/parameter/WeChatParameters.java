/**
 *
 */
package wang.yongrui.checklist.wechat.parameter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;

/**
 * @author Ryan Wang
 *
 */
@Component
@PropertySource({ "classpath:weChat.properties" })
@Getter
public class WeChatParameters {

	@Value("${weChat.appid}")
	private String weChatAppId;

}
