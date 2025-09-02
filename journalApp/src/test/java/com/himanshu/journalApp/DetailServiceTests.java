package com.himanshu.journalApp;


import com.himanshu.journalApp.entity.Users;
import com.himanshu.journalApp.service.DetailService;
import com.himanshu.journalApp.service.UserRepoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DetailServiceTests {

    @InjectMocks
    private DetailService detailService;

    @Mock
    private UserRepoService userRepoService;

    @BeforeEach
    public  void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testLoadUserByUsername_UserExists() {
        // Arrange
        Users mockUser = new Users();
        mockUser.setUsername("john");
        mockUser.setPassword("encodedpassword");
        mockUser.setRole("ROLE_USER");

        when(userRepoService.getUserByName("john")).thenReturn(mockUser);

        // Act
        UserDetails result = detailService.loadUserByUsername("john");

        // Assert
        assertNotNull(result);
        assertEquals("john", result.getUsername());
        assertEquals("encodedpassword", result.getPassword());
        assertTrue(result.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));

    }


}
