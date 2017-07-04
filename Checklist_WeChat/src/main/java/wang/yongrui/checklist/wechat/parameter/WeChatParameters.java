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

	// WeChat parameters
	@Value("${weChat.appid}")
	private String weChatAppId;

	@Value("${weChat.secret}")
	private String weChatSecret;

	@Value("${weChat.access_token_url}")
	private String weChatAccessTokenUrl;

	@Value("${weChat.user_list_url}")
	private String weChatUserListUrl;

	@Value("${weChat.user_list_url_param}")
	private String weChatUserListUrlParam;

	@Value("${weChat.oauth_authorize_snsapi_base_url}")
	private String weChatOAuthAuthorizeSnsapiBaseUrl;

	@Value("${weChat.oauth_authorize_snsapi_userinfo_url}")
	private String weChatOAuthAuthorizeSnsapiUserinfoUrl;

	@Value("${weChat.oauth_authorize_redirect_base_uri}")
	private String weChatOAuthAuthorizeRedirectBaseUri;

	@Value("${weChat.oauth_access_token_url}")
	private String weChatOAuthAccessTokenUrl;

	@Value("${weChat.oauth_refresh_token_url}")
	private String weChatOAuthRefreshTokenUrl;

	@Value("${weChat.oauth_user_info_url}")
	private String weChatOAuthUserInfoUrl;

	@Value("${weChat.oauth_check_access_token_url}")
	private String weChatOAuthCheckAccessTokenUrl;

	@Value("${weChat.user_info_url}")
	private String weChatUserInfoUrl;

	@Value("${weChat.message_template_url}")
	private String weChatMessageTemplateUrl;

	@Value("${weChat.message_template_id}")
	private String weChatMessageTemplateId;

}
