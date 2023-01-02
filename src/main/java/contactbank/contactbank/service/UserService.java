package contactbank.contactbank.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import contactbank.contactbank.dto.UserRegistrationDto;
import contactbank.contactbank.model.User;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}