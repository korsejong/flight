package com.github.korsejong.flight.controller.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.korsejong.flight.domain.member.Member;
import com.github.korsejong.flight.domain.member.MemberRepository;
import com.github.korsejong.flight.service.member.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberControllerTest {
    private MockMvc mockMvc;

    @MockBean
    MemberController memberController;

    @Autowired
    MemberRepository memberRepository;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void postMemberSignUpTest() throws Exception{
        ObjectMapper om = new ObjectMapper();
        Map<String,String> memberInfo = new HashMap<String,String>();
        memberInfo.put("email","sejong");
        memberInfo.put("password","pass");
        String str = om.writeValueAsString(memberInfo);
        mockMvc.perform(post("/user/signup").contentType("application/json;charset=utf-8").content(str))
                .andExpect(status().isOk())
                .andDo(print());
    }
}