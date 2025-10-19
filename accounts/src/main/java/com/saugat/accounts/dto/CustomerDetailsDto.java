package com.saugat.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
        name = "CustomerDetails",
        description = "Schema to hold Customer details information"
)
public class CustomerDetailsDto {
    @Schema(
            description = "Name of the customer", example = "Eazy Bytes"
    )
    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The name length must be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer", example = "tutor@eazybytes.com"
    )
    @NotEmpty(message = "Email can not be null or empty")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(
            description = "Mobile number of the customer", example = "9876543210"
    )
    @NotEmpty(message = "Mobile number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )
    private AccountsDto accountsDto;

    @Schema(
            description = "Loans details of the Customer"
    )
    private LoansDto loansDto;

    @Schema(
            description = "Cards details of the Customer"
    )
    private CardsDto cardsDto;
}
