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
import tatew.ryoko.exception.CreateRegionException;
import tatew.ryoko.exception.GetRegionException;
import tatew.ryoko.model.db.Region;
import tatew.ryoko.model.dto.ErrorDto;
import tatew.ryoko.service.RegionService;

import java.util.List;

@Slf4j
@RestController
public class RegionController
{
    @Autowired
    private RegionService regionService;

    @Operation(
            operationId = "getAllRegions",
            summary = "Gets all regions",
            tags = {"regions-controller"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Region.class)))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @GetMapping(value = "/regions", produces = {"application/json"})
    public @ResponseBody ResponseEntity<Iterable<Region>> getRegions(
            @Parameter(description = "Whether to include archived activities", required = false)
            @RequestParam(name = "archived", required = false) boolean archived)
    {
        var regions = regionService.getAllRegions(archived);
        return ResponseEntity.status(HttpStatus.OK).body(regions);
    }

    @Operation(
            operationId = "getRegionById",
            summary = "Gets a region by ID",
            tags = {"regions-controller"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Region.class))
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
    @GetMapping(value = "/regions/{regionId}", produces = {"application/json"})
    public @ResponseBody ResponseEntity<Region> getRegionById(
            @Parameter(description = "The id of the region to return", required = true)
            @PathVariable(value = "regionId") long id) throws GetRegionException
    {
        Region region = regionService.getRegionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(region);
    }

    @Operation(
            operationId = "postRegion",
            summary = "Creates a new Region, or updates an existing region",
            tags = "regions-controller",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Region Created"),
                    @ApiResponse(responseCode = "400", description = "BadRequest", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"Invalid region data\"],\"status\":\"BAD_REQUEST\"}"))
                    }),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorDto.class),
                                    examples = @ExampleObject(value = "{\"messages\":[\"An unexpected server error occurred\"],\"status\":\"INTERNAL_SERVER_ERROR\"}"))
                    })
            }
    )
    @PostMapping(value = "/regions", consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Void> addUpdateRegion(
            @Parameter(description = "Create a new region or update an existing region", required = true)
            @Valid
            @RequestBody
            Region region) throws CreateRegionException
    {
        Region createdRegion = regionService.createRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/regions/" + createdRegion.getId())
                .build();
    }

    @Operation(
            operationId = "archiveRegion",
            summary = "Archives a region",
            tags = "regions-controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Region Archived"),
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
    @PostMapping(value = "/regions/{regionId}/archive")
    public @ResponseBody ResponseEntity<Void> archiveRegion(
            @Parameter(description = "The id of the region to archive", required = true)
            @PathVariable(value = "regionId") long id) throws GetRegionException
    {
        regionService.archiveRegion(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            operationId = "deleteRegion",
            summary = "Deletes a region",
            tags = "regions-controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Region Deleted"),
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
    @DeleteMapping(value = "/regions/{regionId}")
    public @ResponseBody ResponseEntity<Void> deleteRegion(
            @Parameter(description = "The id of the region to delete", required = true)
            @PathVariable(value = "regionId") long id) throws GetRegionException
    {
        regionService.deleteRegion(id);
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

    @ExceptionHandler(CreateRegionException.class)
    public ResponseEntity<ErrorDto> handleCreateRegionException(CreateRegionException e)
    {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorDto(List.of(e.getMessage()), HttpStatus.BAD_REQUEST));
    }
}
