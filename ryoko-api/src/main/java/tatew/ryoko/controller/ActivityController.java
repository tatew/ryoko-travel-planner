package tatew.ryoko.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tatew.ryoko.model.db.Activity;
import tatew.ryoko.repository.ActivityRepository;

@Slf4j
@RestController
@RequestMapping("/activities")
public class ActivityController
{
    @Autowired
    private ActivityRepository activityRepository;

    @ResponseBody
    @Operation(
            operationId = "getAllActivities",
            summary = "Gets all activities",
            tags = {"activities-controller"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Activity.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @GetMapping(value = "")
    public ResponseEntity<Iterable<Activity>> getActivities()
    {
        var activities = activityRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @Operation(
            operationId = "postActivity",
            summary = "Creates a new Activity",
            tags = "activities-controller",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Activity Created"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error")
            }
    )
    @PostMapping(value = "", consumes = {"application/json"})
    public ResponseEntity<Void> addActivity(
            @Parameter(description = "Create a new activity", required = true)
            @Valid
            @RequestBody
            Activity activity)
    {
        Activity createdActivity = activityRepository.save(activity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/activities/" + createdActivity.id())
                .build();
    }
}
