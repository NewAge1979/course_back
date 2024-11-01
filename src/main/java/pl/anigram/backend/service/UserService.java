package pl.anigram.backend.service;

import pl.anigram.backend.model.entity.User;

public interface UserService {
    public void signUp(User user);
}