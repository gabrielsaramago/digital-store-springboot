package com.gsaramago.digitalstorespring.services;

import com.gsaramago.digitalstorespring.model.DTO.UserDto;
import com.gsaramago.digitalstorespring.model.User;
import com.gsaramago.digitalstorespring.repositories.UserRepository;
import com.gsaramago.digitalstorespring.services.exceptions.DatabaseException;
import com.gsaramago.digitalstorespring.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public List<UserDto> findAll(){
        return userRepository
                .findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public UserDto findById(Long id){
        User user = userRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(id));
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto createUser(User user){
        UserDto userDto = modelMapper.map(userRepository.save(user), UserDto.class);
        return userDto;
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(id));
        if(user.getOrders().isEmpty()){
            userRepository.deleteById(id);
        }
        else {
            throw new DatabaseException("Not allowed deleting users with orders");
        }
    }

    public UserDto updateUser(Long id, User user){
        try {
            User entity = userRepository.getReferenceById(id);
            updateUserParameters(entity, user);
            return modelMapper.map(userRepository.save(entity), UserDto.class);
        }
        catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateUserParameters(User entity, User user) {
        entity.setName(user.getName());
        entity.setPhone(user.getPhone());
    }

}
