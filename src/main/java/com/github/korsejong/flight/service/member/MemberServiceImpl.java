package com.github.korsejong.flight.service.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.member.MemberRepository;
import com.github.korsejong.flight.domain.member.MemberRole;
import com.github.korsejong.flight.domain.path.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    public Member signUp(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        return repository.save(member);
    }

    @Override
    public Member getMemberByEmail(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    @Override
    public void addPath(Member member, Path path) {
        member.getPaths().add(path);
    }

    @Override
    public List<Path> getAllPaths(Member member) {
        List<Path> justPathInfoList = new ArrayList<>();
        for(Path p: member.getPaths()){
            justPathInfoList.add(new Path(p.getFromLat(),p.getFromLng(),p.getToLat(),p.getToLng()));
        }
        return justPathInfoList;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new User(member.getEmail(),member.getPassword(), authorities(member.getMemberRole()));
    }

    private Collection<? extends GrantedAuthority> authorities(MemberRole memberRole) {
        return Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + memberRole.name()));
    }

}
