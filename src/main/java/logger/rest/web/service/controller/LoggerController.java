package logger.rest.web.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import logger.rest.web.service.domain.LogRecord;
import logger.rest.web.service.service.LogRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/logs", produces = APPLICATION_JSON_VALUE)
public class LoggerController {

    private static final Logger logger = LoggerFactory.getLogger(LoggerController.class);
    private final LogRecordService service;

    public LoggerController(LogRecordService logRecordService) {
        this.service = logRecordService;
    }

    @Operation(summary = "Save log record")
    @ApiResponse(responseCode = "201", description = "Log record is saved",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = LogRecord.class))})
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<LogRecord> save(@RequestBody LogRecord logRecord) {
        logger.info("Save log record : {}", logRecord.toString());
        LogRecord savedLogRecord = service.save(logRecord);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLogRecord);
    }

    @Operation(summary = "Return a list of log records based on the query parameters")
    @ApiResponse(responseCode = "200", description = "Log records were found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))})
    @GetMapping
    public ResponseEntity<List<LogRecord>> search(@RequestParam Map<String, String> queryParams) {
        logger.info("Search log records by query params : {}", queryParams);
        return ResponseEntity.ok(service.findAllByParameters(queryParams));
    }

    @Operation(summary = "Return a list of log records by userId")
    @ApiResponse(responseCode = "200", description = "Log records were found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))})
    @GetMapping("/user/{id}")
    public ResponseEntity<List<LogRecord>> searchByUserId(@PathVariable String id) {
        logger.info("Search log records by userId : {}", id);
        return ResponseEntity.ok(service.findAllByUserId(id));
    }

    @Operation(summary = "Return a list of log records by action type")
    @ApiResponse(responseCode = "200", description = "Log records were found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))})
    @GetMapping("/action/{type}")
    public ResponseEntity<List<LogRecord>> searchByActionType(@PathVariable String type) {
        logger.info("Search log records by type : {}", type);
        return ResponseEntity.ok(service.findAllByActionType(type));
    }

    @Operation(summary = "Return a list of log records by time range")
    @ApiResponse(responseCode = "200", description = "Log records were found",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = List.class))})
    @GetMapping("/time")
    public ResponseEntity<List<LogRecord>> searchByTimeRange(@RequestParam String start, @RequestParam String end) {
        logger.info("Search log records by time range : from '{}' to '{}'", start, end);
        return ResponseEntity.ok(service.findAllByActionTimeBetween(
                LocalDateTime.parse(start, DateTimeFormatter.ISO_DATE_TIME),
                LocalDateTime.parse(end, DateTimeFormatter.ISO_DATE_TIME)));
    }

    @Operation(summary = "Return a log record by its userId and sessionId")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the log record",
            content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = LogRecord.class))}),
            @ApiResponse(responseCode = "404", description = "Log record not found", content = @Content)})
    @GetMapping("/user/{userId}/session/{sessionId}")
    public ResponseEntity<LogRecord> loadOrder(@PathVariable String userId, @PathVariable String sessionId) {
        logger.info("Search log records by userId '{}' and sessionId '{}'", userId, sessionId);
        final Optional<LogRecord> logRecord = service.findByUserIdAndSessionId(userId, sessionId);
        if (logRecord.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(logRecord.get());
    }

}
