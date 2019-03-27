package com.github.korsejong.flight.controller.member;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.path.Path;
import com.github.korsejong.flight.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping(value="/signUp", method=RequestMethod.POST)
    public Member signUp(@RequestBody Member member){
        return memberService.signUp(member);
    }

    @RequestMapping(value="/paths/all", method=RequestMethod.GET)
    public List<Path> getAllPaths(@AuthenticationPrincipal User user){
        return memberService.getAllPaths(memberService.getMemberByEmail(user.getUsername()));
    }
}