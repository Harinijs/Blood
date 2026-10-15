package com.blood;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.blood.Controller.LoginController;
import com.blood.Domain.Donor;
import com.blood.Repository.DonorRepository;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DonorRepository repo;

    @Test
    void testLogin() throws Exception {

        Donor donor = new Donor();
        donor.setEmail("test@example.com");
        donor.setPassword("password123");

        when(repo.findByEmailAndPassword(
                "test@example.com", "password123"))
                .thenReturn(donor);

        mockMvc.perform(post("/signin")
                .param("email", "test@example.com")
                .param("password", "password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard"));
    }
}