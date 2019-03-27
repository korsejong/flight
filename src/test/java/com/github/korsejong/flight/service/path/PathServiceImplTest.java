package com.github.korsejong.flight.service.path;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.member.MemberRepository;
import com.github.korsejong.flight.domain.path.Path;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PathServiceImplTest {
    @Autowired
    PathService service;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void savePath() {
        String email = "email.com";
        String password = "password";
        String fromLat = "111.11";
        String fromLng = "132.11";
        String toLat = "100.99";
        String toLng = "99.99";
        Member member = new Member(email,password);
        memberRepository.save(member);
        Path path = new Path(fromLat,fromLng,toLat,toLng);
        System.out.println(service.savePath(path,member));
    }
}