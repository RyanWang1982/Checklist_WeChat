/**
 * 
 */
package wang.yongrui.checklist.wechat.controller;

import static wang.yongrui.checklist.wechat.constant.SystemConstant.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.ApiOperation;
import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.parameter.WeChatParameters;
import wang.yongrui.checklist.wechat.service.WeChatService;

/**
 * @author Ryan Wang
 *
 */
@RestController
@RequestMapping(WECHAT_URI_PREFIX)
public class WeChatController {

	private static final String USER_INFO_URI = "/userInfo";

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Autowired
	private WeChatParameters weChatParameters;

	@Autowired
	private WeChatService weChatService;

	@ApiOperation(value = "This api is only for Wechat, not for public", hidden = true)
	@GetMapping(AUTHENTICATION_URI)
	public ModelAndView getWeChatAuthenticate(HttpServletRequest request) throws UnsupportedEncodingException {
		String redirectBackUrl = request.getRequestURL().toString().replace(AUTHENTICATION_URI, USER_INFO_URI);
		redirectBackUrl = URLEncoder.encode(redirectBackUrl, CHARACTER_ENCODING_UTF8);
		String weChatOAuthRedirectUrl = weChatParameters.getOAuthAuthorizeSnsapiBaseUrl(redirectBackUrl);

		return new ModelAndView(new RedirectView(weChatOAuthRedirectUrl), null);
	}

	@ApiOperation(value = "This api is only for Wechat, not for public", hidden = true)
	@GetMapping(USER_INFO_URI)
	public ModelAndView getWeChatUserInfo(@RequestParam(required = true) String code, HttpServletRequest request,
			HttpServletResponse response) {
		String redirectUrl = null;

		User user = weChatService.authenticateViaWeChatOA(code);
		if (null == user) {
			// TODO Redirect to register page;
			// redirectUrl = "registerPage";
		} else {
			redirectUrl = requestCache.getRequest(request, response).getRedirectUrl();
			request.getSession().setAttribute(CURRENT_AUTHENTICATED_USER, user);
		}

		return null == redirectUrl ? null : new ModelAndView(new RedirectView(redirectUrl), null);
	}

}
