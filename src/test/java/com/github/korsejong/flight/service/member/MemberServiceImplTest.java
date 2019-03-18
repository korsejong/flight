package com.github.korsejong.flight.service.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.member.MemberRepository;
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
    MemberRepository repository;

    @Test
    public void signupTest(){
        String email = "testemail@email.com";
        String password = "passwordTest";
        Member member = new Member(email,password);
        System.out.println(service.signup(member).toString());
    }

    @Test
    public void signinTest(){

    }

    @Test
    public void signoutTest(){

    }
}