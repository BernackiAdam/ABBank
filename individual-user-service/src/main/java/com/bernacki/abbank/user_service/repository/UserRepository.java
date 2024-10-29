package com.bernacki.abbank.user_service.repository;

import com.bernacki.abbank.user_service.entity.IndividualUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<IndividualUser, Long> {
}
