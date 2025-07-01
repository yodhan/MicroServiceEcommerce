package com.ecommerce.microservice.admin_service.service.impl;

import com.ecommerce.microservice.admin_service.dto.UserResponseDTO;
import com.ecommerce.microservice.admin_service.service.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserServiceImpl implements AdminUserService {

    private final RestClient restClient = RestClient.builder().build();
    private static final String USER_SERVICE_BASE_URL = "http://USER-SERVICE/api/users";

    @Override
    public List<UserResponseDTO> getAllUsers() {
        UserResponseDTO[] users = restClient.get()
                .uri(USER_SERVICE_BASE_URL)
                .retrieve()
                .body(UserResponseDTO[].class);

        return Arrays.asList(users != null ? users : new UserResponseDTO[0]);
    }

    @Override
    public void updateUserStatus(String userId, boolean enabled) {
        restClient.put()
                .uri(USER_SERVICE_BASE_URL + "/" + userId + "/status?enabled=" + enabled)
                .retrieve();
    }

    @Override
    public void deleteUser(String userId) {
        restClient.delete()
                .uri(USER_SERVICE_BASE_URL + "/" + userId)
                .retrieve();
    }
}
