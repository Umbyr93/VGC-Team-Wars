package org.urusso.vgcteamwars.domain.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.urusso.vgcteamwars.common.constants.ApiConst;
import org.urusso.vgcteamwars.common.constants.SwaggerTag;
import org.urusso.vgcteamwars.domain.user.dto.*;
import org.urusso.vgcteamwars.domain.user.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConst.USER_API)
@Tag(name = SwaggerTag.USER_TAG_NAME, description = SwaggerTag.USER_TAG_DESC)
public class UserController {
    private final UserService userService;

    @Operation(summary = "Create user", description = "API to create a user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully created")
    })
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        CreateUserResponse createdUser = userService.createUser(request);
        return ResponseEntity.ok(createdUser);
    }

    @Operation(summary = "Login user", description = "API for the user authentication")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User logged in")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest request) {
        LoginUserResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Add user to team", description = "API to add a user to the team")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User added")
    })
    @PatchMapping("register-team")
    public ResponseEntity<Void> addUserToTeam(@Valid @RequestBody UserTeamRequest request) {
        userService.addUserToTeam(request);
        return ResponseEntity.ok().build();
    }
}
