package com.gsaramago.digitalstorespring.services;

import com.gsaramago.digitalstorespring.model.User;
import com.gsaramago.digitalstorespring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return opt.get();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
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
