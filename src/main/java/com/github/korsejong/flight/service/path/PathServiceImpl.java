package com.github.korsejong.flight.service.path;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.path.Path;
import com.github.korsejong.flight.domain.path.PathRepository;
import com.github.korsejong.flight.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PathServiceImpl implements PathService {
    @Autowired
    PathRepository repository;
    @Autowired
    MemberService memberService;

    @Override
    public Path savePath(Path path, Member member) {
        path.setMember(member);
        Path savedPath = repository.save(path);
        memberService.addPath(member, savedPath);
        return savedPath;
    }


}
