package com.kennesaw.rewardsmanagementsystem.service;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rws")
public class RewardsManagementService {
	
	@ApiOperation(value = "helloWorld", notes = "Hello World",
			httpMethod = "GET", consumes = "application/json", produces = "application/json")
		    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Success", response = String.class),
	            @ApiResponse(code = 204, message = "Resource Unavailable"),
	            @ApiResponse(code = 400, message = "Bad Request"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 500, message = "Internal server error"),
	            @ApiResponse(code = 503, message = "Service Unavailable"),
	            @ApiResponse(code = 504, message = "Service Time Out")})
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/helloWorld")
	public @ResponseBody String getHelloWorld(@RequestParam(name="name", required=true) String name) {
		return "Hello, " + name;
	}

}
