package org.urusso.vgcteamwars.domain.war.controller;

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
import org.urusso.vgcteamwars.domain.war.dto.CreateWarRequest;
import org.urusso.vgcteamwars.domain.war.dto.CreateWarResponse;
import org.urusso.vgcteamwars.domain.war.dto.WarResponse;
import org.urusso.vgcteamwars.domain.war.service.WarService;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConst.WAR_API)
@Tag(name = SwaggerTag.WAR_TAG_NAME, description = SwaggerTag.WAR_TAG_DESC)
public class WarController {
    private final WarService warService;

    @Operation(summary = "Create war", description = "API to create a war")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "War successfully created")
    })
    @PostMapping
    public ResponseEntity<CreateWarResponse> createWar(@Valid @RequestBody CreateWarRequest request) {
        CreateWarResponse created = warService.createTeam(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Find war by id", description = "API to find a war id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "War found")
    })
    @GetMapping("{warId}")
    public ResponseEntity<WarResponse> findTeamById(@PathVariable Long warId) {
        WarResponse found = warService.getTeamById(warId);
        return ResponseEntity.ok(found);
    }
}
