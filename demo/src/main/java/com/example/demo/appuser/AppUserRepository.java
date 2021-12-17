package com.example.demo.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
	Optional<AppUser> getElementById(int id);
	void deleteById(int id);
    Optional<AppUser> findById(String email);
    Optional<AppUser> findByEmail(String email);
}
