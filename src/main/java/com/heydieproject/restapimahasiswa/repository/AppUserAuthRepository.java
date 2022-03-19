package com.heydieproject.restapimahasiswa.repository;

import com.heydieproject.restapimahasiswa.entity.AppUserAuth;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AppUserAuthRepository extends PagingAndSortingRepository<AppUserAuth, Long> {

    public Optional<AppUserAuth> findByEmail(String email);
}
