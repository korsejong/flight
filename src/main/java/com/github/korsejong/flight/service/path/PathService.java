package com.github.korsejong.flight.service.path;

import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.path.Path;

public interface PathService {
    Path savePath(Path path,Member member);
}
