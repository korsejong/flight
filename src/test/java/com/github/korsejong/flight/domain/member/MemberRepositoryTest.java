package com.github.korsejong.flight.domain.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository repository;
    @Test
    public void createTest(){
        Member member = new Member();
        member.setEmail("sejong@mail.com");
        member.setPassword("password");
        repository.save(member);

        Optional<Member> maybeMember;
        maybeMember = repository.findByEmail("sejong@mail.com");
        if(maybeMember.isPresent()){
            System.out.println(maybeMember.toString());
        }else{
            System.out.println("Not found memeber");
        }
    }
    @Test
    public void readTest(){
        for (int i = 0; i < 10; i++) {
            Member member = new Member();
            member.setEmail("sejong@mail.com" + i);
            member.setPassword("password");
            member.setMemberRole(MemberRole.USER);
            repository.save(member);
        }

        List<Member> members = repository.findAll();
        for(Member m: members){
            System.out.println(m.toString());
        }
    }
    @Test
    public void updateTest(){

    }
    @Test
    public void deleteTest(){

    }
}