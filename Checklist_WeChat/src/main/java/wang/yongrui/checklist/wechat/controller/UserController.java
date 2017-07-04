/**
 * 
 */
package wang.yongrui.checklist.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import wang.yongrui.checklist.wechat.entity.web.User;
import wang.yongrui.checklist.wechat.service.UserService;

/**
 * @author Ryan Wang
 * 
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user, HttpServletRequest request) {
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

	@GetMapping("/{weChatUnionId}/authentication")
	public ResponseEntity<User> login(@PathVariable String weChatUnionId, HttpServletRequest request) {
		User user = userService.retrieveOneByWeChatUnionId(weChatUnionId);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				user.getUsername(), user.getPassword());
		try {
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		}

		return null;
	}
}
