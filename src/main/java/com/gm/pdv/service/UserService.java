package com.gm.pdv.service;

import com.gm.pdv.dto.UserDTO;
import com.gm.pdv.dto.UserResponseDTO;
import com.gm.pdv.entity.User;
import com.gm.pdv.exceptions.NoItemException;
import com.gm.pdv.repository.UserRepository;
import com.gm.pdv.security.SecurityConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private ModelMapper mapper = new ModelMapper();

    @Transactional
    public List<UserResponseDTO> findAll(){
        List<User> result = userRepository.findAll();
        return result.stream().map(user ->
                new UserResponseDTO(user.getId(),user.getName(), user.getUsername(),user.isEnabled()))
                .collect(Collectors.toList());
    }

    public UserDTO save(UserDTO user) {
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        User userToSave = mapper.map(user, User.class);
        userRepository.save(userToSave);
        return new UserDTO(userToSave.getId(),userToSave.getName(),userToSave.getUsername(),userToSave.getPassword(), userToSave.isEnabled());
    }

    public UserDTO findById(Long id) {
        Optional<User> optional = userRepository.findById(id);

        if (!optional.isPresent()){
            throw new NoItemException("User with id "  +id + " not found.");
        }
        User user = optional.get();
        return new  UserDTO(user.getId(),user.getName(),user.getUsername(),user.getPassword(), user.isEnabled());
    }

    public void deleteById(Long id) {
         userRepository.deleteById(id);
    }

    public UserDTO update(UserDTO user) {
        user.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
        User userToSave = mapper.map(user,User.class);
        Optional<User> userToEdit = userRepository.findById(user.getId());
        if (!userToEdit.isPresent()){
            throw new NoItemException("User not found. ");
        }
        userRepository.save(userToSave);
        return new  UserDTO(userToSave.getId(),userToSave.getName(),userToSave.getUsername(),userToSave.getPassword(), userToSave.isEnabled());
    }

    public User getByUserName(String username) {
        return userRepository.findByUserName(username);
    }
}
