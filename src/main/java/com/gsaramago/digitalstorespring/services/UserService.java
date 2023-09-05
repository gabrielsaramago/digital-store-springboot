package com.gsaramago.digitalstorespring.services;

import com.gsaramago.digitalstorespring.model.User;
import com.gsaramago.digitalstorespring.repositories.UserRepository;
import com.gsaramago.digitalstorespring.services.exceptions.DatabaseException;
import com.gsaramago.digitalstorespring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> opt = userRepository.findById(id);
        return opt.orElseThrow(()-> new ResourceNotFoundException(id));
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        User user = findById(id);
        if(user.getOrders().isEmpty()){
            userRepository.deleteById(id);
        }
        else {
            throw new DatabaseException("Not allowed deleting users with orders");
        }


    }

    public User updateUser(Long id, User user){
        User entity = userRepository.getReferenceById(id);
        updateUserParameters(entity, user);
        return userRepository.save(entity);
    }

    private void updateUserParameters(User entity, User user) {
        entity.setName(user.getName());
        entity.setPhone(user.getPhone());
    }

}
