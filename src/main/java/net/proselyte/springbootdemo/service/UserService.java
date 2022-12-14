package net.proselyte.springbootdemo.service;

import net.proselyte.springbootdemo.model.User;
import net.proselyte.springbootdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add(User user) {
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User getUser(int id) {
        return userRepository.getOne(id);
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
