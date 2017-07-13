/**
 *
 */
package wang.yongrui.checklist.wechat.controller;

import static wang.yongrui.checklist.wechat.constant.SystemConstant.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import io.swagger.annotations.ApiOperation;
import wang.yongrui.checklist.wechat.entity.validation.UserCreateValidator;
import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.parameter.WeChatParameters;
import wang.yongrui.checklist.wechat.service.UserService;

/**
 * @author Ryan Wang
 *
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	private static final String WECHAT_USER_INFO_URI = "/weChat_userInfo";

	private static final String WECHAT_AUTHENTICATION_URI = "/weChat_authentication";

	@Autowired
	private UserService userService;

	@Autowired
	private WeChatParameters weChatParameters;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<User> create(@Validated({ UserCreateValidator.class }) @RequestBody User user,
			HttpServletRequest request) {
		return null;
	}

	@GetMapping
	@PreAuthorize("hasPermission('User', 'Retrieve')")
	public ResponseEntity<User> retrieve(HttpServletRequest request) {
		return null;
	}

	@PutMapping
	@PreAuthorize("hasPermission('User', 'Update')")
	public ResponseEntity<User> putUpdate(@RequestBody User user, HttpServletRequest request) {
		return null;
	}

	@PatchMapping
	@PreAuthorize("hasPermission('User', 'Update')")
	public ResponseEntity<User> patchUpdate(@RequestBody User user, HttpServletRequest request) {
		return null;
	}

	// @ApiOperation(value = "Authenticate the user by WeChat UnionId via WeChat
	// OAuth", notes = "This Api is only for WeChat")
	// @ApiImplicitParam(name = "weChatUnionId", value = "WeChat UnionId",
	// required = true, paramType = "path")
	// @ApiResponses({ @ApiResponse(code = 200, message = "Authenticated the
	// user by WeChat UnionId Successfully"),
	// @ApiResponse(code = 401, message = "Authenticate Failed") })

	@ApiOperation(value = "This api is only for Wechat, not for public", hidden = true)
	@GetMapping(WECHAT_AUTHENTICATION_URI)
	public ModelAndView getWeChatAuthenticate(HttpServletRequest request) throws UnsupportedEncodingException {
		// TODO Cache request --- for redirecting back to the request before
		// authentication

		String redirectBackUrl = request.getRequestURL().toString().replace(WECHAT_AUTHENTICATION_URI,
				WECHAT_USER_INFO_URI);
		redirectBackUrl = URLEncoder.encode(redirectBackUrl, CHARACTER_ENCODING_UTF8);
		String weChatOAuthRedirectUrl = weChatParameters.getOAuthAuthorizeSnsapiUserinfoUrl(redirectBackUrl);

		return new ModelAndView(new RedirectView(weChatOAuthRedirectUrl), null);
	}

	@ApiOperation(value = "This api is only for Wechat, not for public", hidden = true)
	@GetMapping(WECHAT_USER_INFO_URI)
	public ModelAndView getWeChatUserInfo(@RequestParam(required = true) String code, HttpServletRequest request) {
		String redirectUrl = "";

		User user = userService.authenticateViaWeChat(code);
		if (null == user) {
			// TODO Redirect to register page;
			// redirectUrl = "registerPage";
		} else {
			// TODO Get cached request
			// redirectUrl = "requestBeforAuthentication";
		}

		return new ModelAndView(new RedirectView(redirectUrl), null);
	}

}
