package com.facultyavailability.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.facultyavailability.commons.constant.UriConstants.User;
import com.facultyavailability.commons.dto.NewUserCreationRequest;
import com.facultyavailability.commons.dto.UserInfoResponse;
import com.facultyavailability.commons.dto.UserInformation;
import com.facultyavailability.commons.service.ServiceResponse;
import com.facultyavailability.commons.util.Protocol;
import com.facultyavailability.service.util.IUserService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;

@RestController
@Api(value = "user", description = "User information")
public class UserController extends BaseController{

	/*private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);*/
	
	@Autowired
	private IUserService userServices;
	
	@ApiOperation(value = "create new user")
	@ApiResponse(code = 200, message = "Successfully new user has been created", response = ServiceResponse.class)
	@RequestMapping(value = User.NEW_USER, method = RequestMethod.POST)
	public ResponseEntity<ServiceResponse> setNewUser(@Valid @RequestBody NewUserCreationRequest user){
		ServiceResponse response = new ServiceResponse();
		if (!userServices.hasUser(user.getNewUser().getUserId())) {
			userServices.setNewUser(user.getNewUser());
			response.setMessage("Successful");
		} else {
			response.setMessage("user is already exist");
		}
   		response.setCode(HttpStatus.OK.toString());
   		response.setProtocol(Protocol.PROTOCOL_JSON);
   		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "get user info for registered users")
	@ApiResponse(code = 200, message = "Successfully user info is fetched", response = UserInfoResponse.class)
	@RequestMapping(value = User.GET_USER_INFO, method = RequestMethod.GET)
	public ResponseEntity<UserInfoResponse> getUserInfo(@RequestParam(value = "user_id", required = true) String userId){
		UserInformation user = null;
		user = userServices.getUserInfo(userId);
		UserInfoResponse response = new UserInfoResponse();
		if (user == null) {
			response.setMessage("User is not created");
		}
		else{
			response.setMessage("Successful");
		}
		response.setUser(user);
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<UserInfoResponse>(response, HttpStatus.OK);
	}
	
	@ApiOperation(value = "update existing user")
	@ApiResponse(code = 200, message = "Successfully user has been updated", response = ServiceResponse.class)
	@RequestMapping(value = User.UPDATE_USER_INFO, method = RequestMethod.POST)
	private ResponseEntity<ServiceResponse> updateExistingUser(@Valid @RequestBody NewUserCreationRequest user) {
		ServiceResponse response = new ServiceResponse();
		if (userServices.validUser(user.getNewUser().getUserId())) {
			userServices.setNewUser(user.getNewUser());
			response.setMessage("Successful");
		} else {
			response.setMessage("incorrect username or password or invalid user");
		}
		response.setCode(HttpStatus.OK.toString());
		response.setProtocol(Protocol.PROTOCOL_JSON);
		return new ResponseEntity<ServiceResponse>(response, HttpStatus.OK);
	}
}
