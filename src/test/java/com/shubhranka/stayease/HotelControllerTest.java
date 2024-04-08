package com.shubhranka.stayease;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shubhranka.stayease.dto.LoginRequest;
import com.shubhranka.stayease.entities.Hotel;
import com.shubhranka.stayease.entities.Role;
import com.shubhranka.stayease.entities.User;
import com.shubhranka.stayease.repositories.UserRepository;
import com.shubhranka.stayease.service.HotelService;
import com.shubhranka.stayease.service.UserService;
import jakarta.annotation.security.RunAs;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = StayeaseApplication.class)
@AutoConfigureMockMvc
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User customer;

    private LoginRequest customerLoginRequest;

    private LoginRequest adminLoginRequest;

    @Before
    public void setUp() {
        User user = new User();
        user.setEmail("user@user.com");
        user.setPassword("password");
        user.setFirstname("User");
        user.setLastname("User");
        if(userRepository.findByEmail(user.getEmail()) == null)
            userService.registerUser(user);

        User admin = new User();
        admin.setEmail("admin@admin.com");
        admin.setPassword("password");
        admin.setFirstname("Admin");
        admin.setLastname("Admin");
        admin.setRole(Role.ADMIN);
        if(userRepository.findByEmail(admin.getEmail()) == null)
            userService.registerUser(admin);

        customerLoginRequest = new LoginRequest();
        customerLoginRequest.setEmail("user@user.com");
        customerLoginRequest.setPassword("password");

        adminLoginRequest = new LoginRequest();
        adminLoginRequest.setEmail("admin@admin.com");
        adminLoginRequest.setPassword("password");


    }

    @Test
    public void testGetHotels() throws Exception {
        String token = userService.loginUser(customerLoginRequest);
        // Test getHotels
        mockMvc.perform(get("/hotels")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFailAddHotelWithoutAdminRole() throws Exception {

        String token = userService.loginUser(customerLoginRequest);
        // Test addHotel
        mockMvc.perform(post("/hotels")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldAddHotelWithAdminRole() throws Exception {

        Hotel hotel = new Hotel();
        hotel.setName("Hotel");
        hotel.setRooms(10);
        hotel.setLocation("Location");
        hotel.setDescription("Description");

        ObjectMapper objectMapper = new ObjectMapper();
        String hotelJson = objectMapper.writeValueAsString(hotel);

        String token = userService.loginUser(adminLoginRequest);

        // Test addHotel
        mockMvc.perform(post("/hotels")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json")
                .content(hotelJson))
                .andExpect(status().isOk());

    }

    @Test
    public void shouldFailDeleteHotelWithoutAdminRole() throws Exception {

        String token = userService.loginUser(customerLoginRequest);
        // Test deleteHotel
        mockMvc.perform(delete("/hotels/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }


    @Test
    public void shouldDeleteHotelWithAdminRole() throws Exception {

        Hotel hotel = new Hotel();
        hotel.setName("Hotel1");
        hotel.setRooms(10);
        hotel.setLocation("Location");
        hotel.setDescription("Description");

        String token = userService.loginUser(customerLoginRequest);

        mockMvc.perform(post("/hotels")
                .header("Authorization", "Bearer " + userService.loginUser(adminLoginRequest))
                .contentType("application/json")
                .content(new ObjectMapper().writeValueAsString(hotel)))
                .andExpect(status().isOk());


        token = userService.loginUser(adminLoginRequest);

        // Test deleteHotel
        mockMvc.perform(delete("/hotels/1")
                .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

    }
}
