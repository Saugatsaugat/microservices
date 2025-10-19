package com.saugat.accounts.controller;

import com.saugat.accounts.dto.CustomerDetailsDto;
import com.saugat.accounts.service.ICustomerDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
@Tag(
        name = "Customer REST API Documentation",
        description = "Fetch operations for Customer REST API"
)
public class CustomerController {

    private ICustomerDetailsService iCustomerDetailsService;

    public CustomerController(ICustomerDetailsService iCustomerDetailsService){
        this.iCustomerDetailsService = iCustomerDetailsService;
    }

    @Operation(
            summary = "Get Customer Detail REST API",
            description = "REST API to fetch Customer, Account, Cards, and Loans details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
                                                                   String mobileNumber) {
        // Implementation goes here
        ResponseEntity<CustomerDetailsDto> customerDetailsDtoResponseEntity = iCustomerDetailsService.fetchCustomerDetails(mobileNumber);
        return customerDetailsDtoResponseEntity;

    }
}
