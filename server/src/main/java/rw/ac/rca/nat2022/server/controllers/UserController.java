package rw.ac.rca.nat2022.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rw.ac.rca.nat2022.server.services.IUserService;
import rw.ac.rca.nat2022.server.utils.ApiResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ApiResponse getAllUsers() {
        return new ApiResponse(HttpStatus.OK, true, "All users fetched", userService.getAllUsers());
    }
}
