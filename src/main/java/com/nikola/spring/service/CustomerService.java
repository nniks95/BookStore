package com.nikola.spring.service;

import com.nikola.spring.dto.CustomerDto;
import com.nikola.spring.utils.RegistrationForm;

public interface CustomerService {

    CustomerDto addCustomer(RegistrationForm registrationForm);



}
