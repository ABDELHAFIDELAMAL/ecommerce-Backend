package com.example.demo.controllers;

import com.example.demo.response.ApiResponse;
import com.example.demo.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IUserService userService ;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers(){
        try{
            var data = userService.getAllUsers();
            return ResponseEntity.ok(new ApiResponse("Users fetched successfully " , data , true ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(e.getMessage(), null , false));
        }
    }
}
