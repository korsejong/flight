package com.github.korsejong.flight.domain.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {
    @Autowired
    UserRepository repository;

    @Test
    public void createTest() {
        String email = "test@mail.com";
        String password = "password";
        User user = new User(email,password);

        String email2 = "test2@mail.com";
        String password2 = "password2";
        User user2 = new User();
        user2.setEmail(email2);
        user2.setPassword(password2);

        repository.save(user);
        repository.save(user2);

        List<User> savedList = repository.findAll();

        for(User e: savedList){
            System.out.println(e.toString());
        }

        assertThat(savedList, hasSize(2));
    }
    @Test
    public void readTest() {

    }
    @Test
    public void updateTest() {

    }
    @Test
    public void deleteTest() {

    }
}
