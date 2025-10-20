package com.saugat.accounts.service.impl;

import com.saugat.accounts.dto.AccountsDto;
import com.saugat.accounts.dto.CardsDto;
import com.saugat.accounts.dto.CustomerDetailsDto;
import com.saugat.accounts.dto.LoansDto;
import com.saugat.accounts.entity.Accounts;
import com.saugat.accounts.entity.Customer;
import com.saugat.accounts.exception.ResourceNotFoundException;
import com.saugat.accounts.mapper.AccountsMapper;
import com.saugat.accounts.mapper.CustomerMapper;
import com.saugat.accounts.repository.AccountsRepository;
import com.saugat.accounts.repository.CustomerRepository;
import com.saugat.accounts.service.ICustomerDetailsService;
import com.saugat.accounts.service.client.CardsFeignClient;
import com.saugat.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceDetailsImpl implements ICustomerDetailsService {

    private CustomerRepository customerRepository;
    private AccountsRepository accountsRepository;
    private LoansFeignClient loansFeignClient;
    private CardsFeignClient cardsFeignClient;

    /**
     * Fetch customer details for the given mobile number.
     *
     * @param mobileNumber the customer's mobile number (must not be null or empty; preferred format: E.164 like +1234567890)
     * @return a {@code ResponseEntity<CustomerDetailsDto>} representing the result:
     * <ul>
     *   <li>200 OK — body contains the customer details when found</li>
     *   <li>404 Not Found — no customer associated with the provided mobile number</li>
     *   <li>400 Bad Request — the provided mobile number is invalid</li>
     *   <li>500 Internal Server Error — unexpected server-side error</li>
     * </ul>
     */
    @Override
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);

        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
