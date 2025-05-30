package tatew.ryoko.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tatew.ryoko.exception.CreateActivityException;
import tatew.ryoko.exception.GetActivityException;
import tatew.ryoko.exception.GetRegionException;
import tatew.ryoko.model.db.Activity;
import tatew.ryoko.model.dto.ErrorDto;
import tatew.ryoko.service.ActivityService;

import java.util.List;

@Slf4j
@RestController
public class ActivityController
{
    @Autowired
    private ActivityService activityService;

    @Operation(
            operationId = "getAllActivities",
            summary = "Gets all activities",
            tags = {"activities-controller"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Activity.class)))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"Region with id 1 not found\"],\"status\":\"NOT_FOUND\"}"))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @GetMapping(value = "/activities", produces = {"application/json"})
    public @ResponseBody ResponseEntity<Iterable<Activity>> getActivities(
            @Parameter(description = "The id of the region to return activities for", required = false)
            @RequestParam(value = "regionId", required = false) Long regionId,
            @Parameter(description = "Whether to include archived activities", required = false)
            @RequestParam(name = "archived", required = false) boolean archived) throws GetRegionException
    {
        Iterable<Activity> activities = null;
        if (regionId != null)
        {
            activities = activityService.getActivitiesByRegionId(regionId, archived);
        }
        else
        {
            activities = activityService.getAllActivities(archived);
        }

        return ResponseEntity.status(HttpStatus.OK).body(activities);
    }

    @Operation(
            operationId = "getActivityById",
            summary = "Gets an activity by ID",
            tags = {"activities-controller"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class))
                    }),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"Activity with id 1 not found\"],\"status\":\"NOT_FOUND\"}"))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @GetMapping(value = "/activities/{activityId}", produces = {"application/json"})
    public @ResponseBody ResponseEntity<Activity> getActivityById(
            @Parameter(description = "The id of the activity to return", required = true)
            @PathVariable(value = "activityId") long id) throws GetActivityException
    {
        Activity activity = activityService.getActivityById(id);
        return ResponseEntity.status(HttpStatus.OK).body(activity);
    }

    @Operation(
            operationId = "postActivity",
            summary = "Creates a new Activity, or updates an existing activity",
            tags = "activities-controller",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Activity Created"),
                    @ApiResponse(responseCode = "400", description = "BadRequest", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"Map provider must be 10 characters or less\"],\"status\":\"BAD_REQUEST\"}"))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @PostMapping(value = "/activities", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Void> addUpdateActivity(
            @Parameter(description = "Create a new activity or update an existing activity", required = true)
            @Valid
            @RequestBody
            Activity activity) throws CreateActivityException
    {
        Activity createdActivity = activityService.createActivity(activity);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/activities/" + createdActivity.getId())
                .build();
    }

    @Operation(
            operationId = "archiveActivity",
            summary = "Archives an activity",
            tags = "activities-controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Activity Archived"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"Activity with id 1 not found\"],\"status\":\"NOT_FOUND\"}"))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @PostMapping(value = "/activities/{activityId}/archive")
    public @ResponseBody ResponseEntity<Void> archiveActivity(
            @Parameter(description = "The id of the activity to archive", required = true)
            @PathVariable(value = "activityId") long id) throws GetActivityException
    {
        activityService.archiveActivity(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            operationId = "deleteActivity",
            summary = "Deletes an activity",
            tags = "activities-controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Activity Deleted"),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"Activity with id 1 not found\"],\"status\":\"NOT_FOUND\"}"))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @DeleteMapping(value = "/activities/{activityId}")
    public @ResponseBody ResponseEntity<Void> deleteActivity(
            @Parameter(description = "The id of the activity to delete", required = true)
            @PathVariable(value = "activityId") long id) throws GetActivityException
    {
        activityService.deleteActivity(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @ExceptionHandler(GetRegionException.class)
    public ResponseEntity<ErrorDto> handleGetRegionException(GetRegionException e)
    {
        MDC.put("regionId", String.valueOf(e.getRegionId()));
        log.error(e.getMessage(), e);
        MDC.remove("regionId");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(List.of(e.getMessage()), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(GetActivityException.class)
    public ResponseEntity<ErrorDto> handleGetActivityException(GetActivityException e)
    {
        MDC.put("activityId", String.valueOf(e.getActivityId()));
        log.error(e.getMessage(), e);
        MDC.remove("activityId");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(List.of(e.getMessage()), HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(CreateActivityException.class)
    public ResponseEntity<ErrorDto> handleCreateActivityException(CreateActivityException e)
    {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(List.of(e.getMessage()), HttpStatus.BAD_REQUEST));
    }
}
