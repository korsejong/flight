package com.github.korsejong.flight.controller.path;

import com.github.korsejong.flight.domain.path.Path;
import com.github.korsejong.flight.service.member.MemberService;
import com.github.korsejong.flight.service.path.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("path")
public class PathController {
    @Autowired
    PathService pathService;
    @Autowired
    MemberService memberService;

    @RequestMapping(method=RequestMethod.POST)
    public void savePath(@RequestBody Path path, @AuthenticationPrincipal User user){
        pathService.savePath(path, memberService.getMemberByEmail(user.getUsername()));
    }
}
