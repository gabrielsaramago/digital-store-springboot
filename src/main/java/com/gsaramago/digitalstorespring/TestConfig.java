package com.gsaramago.digitalstorespring;

import com.gsaramago.digitalstorespring.model.Category;
import com.gsaramago.digitalstorespring.model.Order;
import com.gsaramago.digitalstorespring.model.User;
import com.gsaramago.digitalstorespring.model.enums.OrderStatus;
import com.gsaramago.digitalstorespring.repositories.CategoryRepository;
import com.gsaramago.digitalstorespring.repositories.OrderRepository;
import com.gsaramago.digitalstorespring.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2023-06-20T19:53:07Z"), u1, OrderStatus.WAITING_PAYMENT);
        Order o2 = new Order(null, Instant.parse("2023-07-21T03:42:10Z"), u2, OrderStatus.PAID);
        Order o3 = new Order(null, Instant.parse("2023-07-22T15:21:22Z"), u1, OrderStatus.DELIVERED);

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));

        userRepository.saveAll(Arrays.asList(u1,u2));

        orderRepository.saveAll(Arrays.asList(o1,o2,o3));



    }
}
