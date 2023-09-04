package com.gsaramago.digitalstorespring.repositories;

import com.gsaramago.digitalstorespring.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
