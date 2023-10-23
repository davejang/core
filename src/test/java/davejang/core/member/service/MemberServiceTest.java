package davejang.core.member.service;

import davejang.core.member.domain.Member;
import davejang.core.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void login() {
        Member member = new Member();
        member.setName("spring123");
        member.setPw("abc");
        member.setEmail("spring@kyungseok.com");

        memberService.join(member);

        Member memberValid = new Member();
        memberValid.setName("spring123");
        memberValid.setPw("abc");

        Member loginMember = memberService.login(memberValid);
        assertEquals(member, loginMember);
    }

    @Test
    void loginFailed() {
        Member member = new Member();
        member.setName("spring123");
        member.setPw("def");
        member.setEmail("spring@kyungseok.com");

        memberService.join(member);

        Member memberValid = new Member();
        memberValid.setName("spring123");
        memberValid.setPw("abc");

        Member loginMember = memberService.login(memberValid);
        assertEquals(loginMember, null);
    }
}