package com.berkayarslan.UserService.repository;

import com.berkayarslan.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
