// package com.demovehiclepro.service.authentication;
//
// import com.demovehiclepro.data.enums.UserType;
// import com.demovehiclepro.data.model.BaseUser;
// import com.demovehiclepro.data.model.Dealer;
// import com.demovehiclepro.repository.DealerRepository;
// import com.demovehiclepro.data.dtos.RegistrationDTO;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import static org.junit.jupiter.api.Assertions.*;
//
// @SpringBootTest
// class DealerAuthServiceImplTest {
//
//     @Autowired
//     DealerAuthService dealerAuthService;
//
//     @Autowired
//     DealerRepository dealerRepository;
//
//     @Test
//     void register() {
//
//         RegistrationDTO newRegistrationDto = new RegistrationDTO("testUser3",
//                 "testuser3@gmail.com", "testuser3",UserType.DEALER);
//
//         Dealer testDealer = new Dealer();
//
//         assertEquals(null,testDealer.getBaseUser());
//
//         testDealer = dealerAuthService.register(newRegistrationDto);
//
//         assertEquals("testUser3",testDealer.getBaseUser().getName());
//     }
// }