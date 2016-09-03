//package mk.gameIt.web.rest;
//
//import mk.gameIt.GameItApplication;
//import mk.gameIt.domain.User;
//import mk.gameIt.repository.UserRepository;
//import mk.gameIt.service.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import static org.junit.Assert.*;
//
///**
// * Created by Stefan on 16.04.2016.
// */
////@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = GameItApplication.class)
//@WebAppConfiguration
//public class AccountControllerTest {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void changeLanguage() throws Exception {
//
//        userService.changeLangKey("mk","admin");
//
//        User admin = userRepository.findOneByUsername("admin");
//        assertEquals("mk",admin.getLangKey());
//    }
//}