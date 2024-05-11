package aiss.VimeoMiner.service;

import aiss.VimeoMiner.VDmodel.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("Get user")
    void getUser() {
        aiss.VimeoMiner.model.User user = userService.getUser("168362");
        assertFalse(user == null, "The specific user has not been found");
        System.out.println(user);

    }
    @Test
    @DisplayName("Get user from a comment")
    void getUserFromComment() {
        User user = userService.getUserFromComment("18772746", "65843743");
        assertFalse(user == null, "The user from the comment has not been found");
        System.out.println(user);

    }
}