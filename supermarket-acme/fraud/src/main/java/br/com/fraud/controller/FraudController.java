package br.com.fraud.controller;

import br.com.fraud.controller.request.FraudRequest;
import br.com.fraud.controller.response.FraudResponse;
import br.com.fraud.service.FraudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/frauds")
@Tag(name = "Api to management frauds", description = "Create and management frauds")
public class FraudController {

    private final FraudService fraudService;

    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @Operation(summary = "create fraud", description = "create fraud to fraud system")
    @ApiResponse(responseCode = "201", description = "Fraud success created")
    @PostMapping
    public ResponseEntity<FraudResponse> createFraud(@RequestBody FraudRequest fraudRequest) {
        log.info("calling controller to create fraud {}", fraudRequest );
        this.fraudService.createFraud(fraudRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "list frauds", description = "list the frauds on the fraud system")
    @ApiResponse(responseCode = "201", description = "Frauds list obtained successfully")
    @GetMapping
    public ResponseEntity<?> listFrauds() {
        log.info("calling controller to get frauds list {}", "" );
        var list =  this.fraudService.listFrauds();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
