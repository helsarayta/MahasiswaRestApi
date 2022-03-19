package com.heydieproject.restapimahasiswa.service.serviceImpl;

import com.heydieproject.restapimahasiswa.entity.AppUserAuth;
import com.heydieproject.restapimahasiswa.repository.AppUserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AppUserAuthService implements UserDetailsService {

    @Autowired
    private AppUserAuthRepository userAuthRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return userAuthRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException
                                (String.format("Username with email '%s' not found", email)));
    }

    public AppUserAuth registerUserAuth(AppUserAuth appUserAuth) {
        boolean present = userAuthRepository.findByEmail(appUserAuth.getEmail()).isPresent();
        if (present) {
            throw new RuntimeException(String.format("Username with email '%s' is exist", appUserAuth.getEmail()));
        }
        String encode = passwordEncoder.encode(appUserAuth.getPassword());
        appUserAuth.setPassword(encode);
        return userAuthRepository.save(appUserAuth);
    }
}
