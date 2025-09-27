package com.saugat.accounts.service;

import com.saugat.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return
     */
    CustomerDto fetchAccountDetails(String mobileNumber);


    /**
     * @param customerDto
     * @return
     */
    boolean updateAccountsDetails(CustomerDto customerDto);

    /**
     * @param mobileNumber
     * @return
     */
    boolean deleteAccountDetails(String mobileNumber);

}
