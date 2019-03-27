package com.github.korsejong.flight.service.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.member.MemberRepository;
import com.github.korsejong.flight.domain.path.Path;
import com.github.korsejong.flight.domain.path.PathRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceImplTest {
    @Autowired
    MemberService service;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    PathRepository pathRepository;
    @Test
    public void signUp() {
        String email = "email@mail.com";
        String password = "password";
        Member member = new Member(email,password);
        System.out.println(service.signUp(member));
    }

    @Test
    public void getMemberByEmail() {
        String email = "email@mail.com";
        String password = "password";
        Member member = new Member(email,password);
        memberRepository.save(member);
        System.out.println(service.getMemberByEmail(email));
    }

    @Test
    public void addPath() {
        String email = "email@mail.com";
        String password = "password";
        String fromLat = "111.11";
        String fromLng = "132.11";
        String toLat = "100.99";
        String toLng = "99.99";
        Member member = new Member(email,password);
        Path path = new Path(fromLat,fromLng,toLat,toLng);
        Member savedMember = memberRepository.save(member);
        path.setMember(savedMember);
        Path savedPath = pathRepository.save(path);
        service.addPath(savedMember,savedPath);
        System.out.println(savedMember.getPaths().toString());
    }

    @Test
    public void getAllPaths() {
        String email = "email@mail.com";
        String password = "password";
        String fromLat = "111.11";
        String fromLng = "132.11";
        String toLat = "100.99";
        String toLng = "99.99";
        Member member = new Member(email,password);
        Path path = new Path(fromLat,fromLng,toLat,toLng);
        Member savedMember = memberRepository.save(member);
        path.setMember(savedMember);
        Path savedPath = pathRepository.save(path);
        service.addPath(savedMember,savedPath);

        fromLat = "113.11";
        fromLng = "134.11";
        toLat = "105.99";
        toLng = "19.99";
        path = new Path(fromLat,fromLng,toLat,toLng);
        path.setMember(savedMember);
        savedPath = pathRepository.save(path);
        service.addPath(savedMember,savedPath);

        System.out.println(service.getAllPaths(savedMember).toString());
    }
}