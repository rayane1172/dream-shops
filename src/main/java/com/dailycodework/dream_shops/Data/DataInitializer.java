package com.dailycodework.dream_shops.Data;

import com.dailycodework.dream_shops.model.User;
import com.dailycodework.dream_shops.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
            createRandomUser();
    }


    private void createRandomUser(){
        for (int i = 1; i < 10; i++) {
            String defaultEmail = "User"+i+"@gmail.com";
            if(userRepository.existsByEmail(defaultEmail)){
                continue;
            }
            User user = new User();
            user.setFirstName("user"+i);
            user.setLastName("user"+i+"ln");
            user.setEmail(defaultEmail);
            user.setPassword("user"+i);
            userRepository.save(user);
            System.out.println("Random user"+i+" generated successfully...");
        }


    }
}
