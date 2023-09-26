package com.spring.boot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.dto.User;
import com.spring.boot.model.SiteUser;

@Repository
public interface MyPageRepository extends JpaRepository<User, String> {
    //MyPage findByEmail(String email);
}