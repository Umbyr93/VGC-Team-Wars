package org.urusso.vgcteamwars.domain.jwt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.urusso.vgcteamwars.common.constants.ApiConst;
import org.urusso.vgcteamwars.common.constants.SwaggerTag;
import org.urusso.vgcteamwars.domain.jwt.dto.RefreshTokenRequest;
import org.urusso.vgcteamwars.domain.jwt.dto.RefreshTokenResponse;
import org.urusso.vgcteamwars.domain.jwt.provider.JwtProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConst.AUTH_API)
@Tag(name = SwaggerTag.AUTH_TAG_NAME, description = SwaggerTag.AUTH_TAG_DESC)
public class JwtController {
    private final JwtProvider jwtProvider;

    @Operation(summary = "Refresh jwt token", description = "API to refresh the JWT token")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Token refreshed successfully")
    })
    @PostMapping("refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtProvider.refreshToken(request));
    }
}
