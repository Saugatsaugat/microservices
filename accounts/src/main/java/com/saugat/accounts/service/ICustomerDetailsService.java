package com.saugat.accounts.service;

import com.saugat.accounts.dto.CustomerDetailsDto;
import org.springframework.http.ResponseEntity;

public interface ICustomerDetailsService {
    /**
     * Fetch customer details for the given mobile number.
     *
     * @param mobileNumber the customer's mobile number (must not be null or empty; preferred format: E.164 like +1234567890)
     * @return a {@code ResponseEntity<CustomerDetailsDto>} representing the result:
     *         <ul>
     *           <li>200 OK — body contains the customer details when found</li>
     *           <li>404 Not Found — no customer associated with the provided mobile number</li>
     *           <li>400 Bad Request — the provided mobile number is invalid</li>
     *           <li>500 Internal Server Error — unexpected server-side error</li>
     *         </ul>
     */
    ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber, String correlationId);

}
