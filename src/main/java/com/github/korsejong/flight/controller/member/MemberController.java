package com.github.korsejong.flight.controller.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value="/signup", method=RequestMethod.POST, produces ="application/json;charset=utf-8")
    public Member signup(@RequestBody Member member){
        return memberService.signup(member);
    }
}