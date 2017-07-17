/**
 *
 */
package wang.yongrui.checklist.wechat.controller;

import static wang.yongrui.checklist.wechat.constant.SystemConstant.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.checklist.wechat.entity.validation.UserCreateValidator;
import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.service.UserService;

/**
 * @author Ryan Wang
 *
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> create(@Validated({ UserCreateValidator.class }) @RequestBody User user,
			HttpServletRequest request) {
		return null;
	}

	@GetMapping
	@PreAuthorize("hasPermission('User', 'Retrieve')")
	public ResponseEntity<User> retrieve(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(CURRENT_AUTHENTICATED_USER);
		return new ResponseEntity<>(user, HttpStatus.OK);
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

}
