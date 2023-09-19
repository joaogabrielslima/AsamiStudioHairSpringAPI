package com.br.asamistudiohair.repository;

import com.br.asamistudiohair.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByEmail(String email);
    User findUserByPassword(String password);
    User findUserByPasswordAndEmail(String password, String email);
}