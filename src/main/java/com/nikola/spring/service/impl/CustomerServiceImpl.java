package com.nikola.spring.service.impl;

import com.nikola.spring.converter.TempConverter;
import com.nikola.spring.dto.CustomerDto;
import com.nikola.spring.dto.ShippingAddressDto;
import com.nikola.spring.dto.UserDto;
import com.nikola.spring.entities.*;
import com.nikola.spring.exceptions.DuplicateNotAllowed;
import com.nikola.spring.repositories.*;
import com.nikola.spring.service.CustomerService;
import com.nikola.spring.utils.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired private UserRepository userRepository;
    @Autowired private BCryptPasswordEncoder passwordEncoder;
    @Autowired private TempConverter tempConverter;
    @Autowired private RoleRepository roleRepository;
    @Autowired private CustomerRepository customerRepository;
    @Autowired private ShippingAddressRepository shippingAddressRepository;
    @Autowired private CartRepository cartRepository;
    @Autowired private WishlistRepository wishlistRepository;

    @Override
    public CustomerDto addCustomer(RegistrationForm registrationForm) {
        UserDto user = registrationForm.getUser();
        userRepository.findByEmail(user.getEmail()).orElseThrow(()-> new DuplicateNotAllowed(new Error("Duplicate not allowed")));
        RoleEntity roleEntity = roleRepository.findByRole("USER").orElse(roleRepository.save(new RoleEntity("USER")));
        List<RoleEntity> roles =  new ArrayList<>();
        roles.add(roleEntity);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled((short) 1);
        UserEntity userEntity = tempConverter.dtoToEntity(user);
        userEntity.setRoles(roles);
        UserEntity storedUsers = userRepository.save(userEntity);
        List<UserEntity> users = roleEntity.getUsers();
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(storedUsers);
        roleEntity.setUsers(users);

        CartEntity cartEntity = new CartEntity();
        cartEntity.setCartPrice(0d);
        CartEntity storedCart = cartRepository.save(cartEntity);
        ShippingAddressDto addressDto = registrationForm.getShippingAddress();
        ShippingAddressEntity storedAddress = shippingAddressRepository.save(tempConverter.dtoToEntity(addressDto));
        WishlistEntity wishlist = new WishlistEntity();
        WishlistEntity storedWishlist = wishlistRepository.save(wishlist);
        return null;
    }
}
