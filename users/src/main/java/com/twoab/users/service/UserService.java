package com.twoab.users.service;

import com.twoab.users.dto.UserDTO;
import com.twoab.users.exception.UserNotFoundException;
import com.twoab.users.model.User;
import com.twoab.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void create(UserDTO userDTO) {
        this.userRepository.save(toEntity(userDTO));
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(UUID id){

        return this.userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("Usuário não encontrado"));
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void update(UUID id, UserDTO userDTO) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Usuário não encontrado"));
        user.setEmail(userDTO.email());
        user.setName(userDTO.name());
        user.setPassword(userDTO.password());

        this.userRepository.save(user);
    }

    public void delete(UUID id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found"));
        this.userRepository.deleteById(id);
    }


    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.name());
        user.setEmail(userDTO.email());
        user.setPassword(userDTO.password());
        return user;
    }
}
