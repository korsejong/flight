package com.github.korsejong.flight.domain.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.member.MemberRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SecurityMember extends User {
    private static final String ROLE_PREFIX = "ROLE_";
    public SecurityMember(Member member) {
        super(member.getEmail(), member.getPassword(), makeGrantedAuthority(member.getMemberRole()));
    }
    private static List<GrantedAuthority> makeGrantedAuthority(MemberRole role){
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
        return list;
    }
}
