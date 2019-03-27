package com.github.korsejong.flight.service.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.path.Path;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService extends UserDetailsService {
    Member signUp(Member member);
    Member getMemberByEmail(String email);
    void addPath(Member member, Path path);
    List<Path> getAllPaths(Member member);
}
