package com.github.korsejong.flight.service.member;

import com.github.korsejong.flight.domain.member.MemberRepository;
import com.github.korsejong.flight.domain.member.SecurityMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return Optional.ofNullable(repository.findByEmail(email))
                .filter(m -> m.isPresent())
                .map(m -> new SecurityMember(m.get())).get();
    }
}
