package com.gsaramago.digitalstorespring.repositories;

import com.gsaramago.digitalstorespring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
