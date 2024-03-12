package com.berkayarslan.UserService.service;

import com.berkayarslan.UserService.general.BaseEntityService;
import com.berkayarslan.UserService.model.User;
import com.berkayarslan.UserService.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseEntityService<User, UserRepository> {
    protected UserService(UserRepository repository) {
        super(repository);
    }
}
