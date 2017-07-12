/**
 *
 */
package wang.yongrui.checklist.wechat.parameter;

import static wang.yongrui.checklist.wechat.constant.SystemConstant.*;

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
	private String appId;

	@Value("${weChat.secret}")
	private String secret;

	@Value("${weChat.access_token_url}")
	private String accessTokenUrl;

	@Value("${weChat.user_list_url}")
	private String userListUrl;

	@Value("${weChat.user_list_url_param}")
	private String userListUrlParam;

	@Value("${weChat.oauth_authorize_snsapi_base_url}")
	private String oAuthAuthorizeSnsapiBaseUrl;

	@Value("${weChat.oauth_authorize_snsapi_userinfo_url}")
	private String oAuthAuthorizeSnsapiUserinfoUrl;

	@Value("${weChat.oauth_authorize_redirect_base_uri}")
	private String oAuthAuthorizeRedirectBaseUri;

	@Value("${weChat.oauth_access_token_url}")
	private String oAuthAccessTokenUrl;

	@Value("${weChat.oauth_refresh_token_url}")
	private String oAuthRefreshTokenUrl;

	@Value("${weChat.oauth_user_info_url}")
	private String oAuthUserInfoUrl;

	@Value("${weChat.oauth_check_access_token_url}")
	private String oAuthCheckAccessTokenUrl;

	@Value("${weChat.user_info_url}")
	private String userInfoUrl;

	@Value("${weChat.message_template_url}")
	private String messageTemplateUrl;

	@Value("${weChat.message_template_id}")
	private String messageTemplateId;

	public String getOAuthAuthorizeSnsapiUserinfoUrl(String redirectUrl) {
		return oAuthAuthorizeSnsapiUserinfoUrl.replace(WECHAT_OAUTH_REDIRECT_URI, redirectUrl);
	}

	public String getOAuthUserInfoUrl(String oAuthAccessToken, String openId) {
		return oAuthUserInfoUrl.replace(WECHAT_OAUTH_ACCESS_TOKEN, oAuthAccessToken).replace(WECHAT_OPENID, openId);
	}

}
