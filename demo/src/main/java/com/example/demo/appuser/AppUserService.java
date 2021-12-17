package com.example.demo.appuser;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG =
            "user with email %s not found";
    
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public AppUserService() {
    }

    public String signUpUser(AppUser appUser) {
        boolean usersExist = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
        if (usersExist) {
            throw new IllegalStateException("email already taken");
        }
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String encodePassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePassword);
        appUserRepository.save(appUser);
        return "it work";
    }
	// public void addNewUser(AppUser user) {
    //     Optional<AppUser> userOptional = repo.findByEmail(user.getEmail());
    //     if (userOptional.isPresent())
    //         throw new IllegalStateException("email token");
    //     repo.save(user);
	// }
    // public void deleteUser(int id) {
    //     boolean exists = repo.existsById(id);
    //     if (!exists) {
    //         throw new IllegalStateException("Student with id " + id + "does not exists");
    //     }
    //     repo.deleteById(id);
    // }

    // @Transactional
    // public void update(User user) {
    //     repo.save(user);
    // }
    // public Iterable<User> getAll() {
    //     return repo.findAll();
    // }

    // public Iterable<User> getUser() {
    //     return repo.findAll();
    // }
}
