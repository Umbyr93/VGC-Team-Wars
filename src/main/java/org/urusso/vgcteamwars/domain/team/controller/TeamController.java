package org.urusso.vgcteamwars.domain.team.controller;

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
import org.urusso.vgcteamwars.domain.team.dto.CreateTeamRequest;
import org.urusso.vgcteamwars.domain.team.dto.CreateTeamResponse;
import org.urusso.vgcteamwars.domain.team.dto.FindTeamResponse;
import org.urusso.vgcteamwars.domain.team.dto.TeamResponse;
import org.urusso.vgcteamwars.domain.team.service.TeamService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiConst.TEAM_API)
@Tag(name = SwaggerTag.TEAM_TAG_NAME, description = SwaggerTag.TEAM_TAG_DESC)
public class TeamController {
    private final TeamService teamService;

    @Operation(summary = "Create team", description = "API to create a team")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Team successfully created")
    })
    @PostMapping
    public ResponseEntity<CreateTeamResponse> createUser(@Valid @RequestBody CreateTeamRequest request) {
        CreateTeamResponse created = teamService.createTeam(request);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Find team by id", description = "API to find a team id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Team found")
    })
    @GetMapping("{teamId}")
    public ResponseEntity<TeamResponse> findTeamById(@PathVariable Long teamId) {
        TeamResponse found = teamService.getTeamById(teamId);
        return ResponseEntity.ok(found);
    }

    @Operation(summary = "Find teams by name", description = "API to find teams by name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Teams found")
    })
    @GetMapping
    public ResponseEntity<List<FindTeamResponse>> findTeamsByName(@RequestParam("name") String name) {
        List<FindTeamResponse> found = teamService.findTeamsByNameLike(name);
        return ResponseEntity.ok(found);
    }
}
