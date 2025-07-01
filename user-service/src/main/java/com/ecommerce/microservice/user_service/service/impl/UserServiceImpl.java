package com.ecommerce.microservice.user_service.service.impl;

import com.ecommerce.microservice.user_service.dto.UserRequestDTO;
import com.ecommerce.microservice.user_service.dto.UserResponseDTO;
import com.ecommerce.microservice.user_service.mapper.UserMapper;
import com.ecommerce.microservice.user_service.model.User;
import com.ecommerce.microservice.user_service.repository.UserRepository;
import com.ecommerce.microservice.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        User user = UserMapper.toEntity(requestDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user= userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(
                        "User not found with id:"+id
                ));
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + username));
        return UserMapper.toDto(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setIsActive(requestDTO.getIsActive());
        user.setUpdatedAt(LocalDateTime.now());

        return UserMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);

    }
}
