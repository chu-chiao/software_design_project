package com.demovehiclepro.service.authentication;

import com.demovehiclepro.data.enums.UserType;
import com.demovehiclepro.data.model.BaseUser;
import com.demovehiclepro.data.model.Dealer;
import com.demovehiclepro.dtos.RegistrationDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DealerAuthServiceImplTest {

    @Autowired
    DealerAuthService dealerAuthService;

    @Test
    void register() {
        RegistrationDTO newRegistrationDto = new RegistrationDTO("testUser",
                "testuser@gmail.com", UserType.DEALER);

        BaseUser testDealer = new Dealer();

        assertEquals(null,testDealer.getName());

        testDealer = dealerAuthService.register(newRegistrationDto);
        assertEquals("testUser",testDealer.getName());
    }
}