package davejang.core.service;

import davejang.core.domain.Member;
import davejang.core.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void join() {
        Member member = new Member();
        member.setPw("abc");
        member.setEmail("spring@kyungseok.com");
        member.setName("spring123");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void duplicateCheck() {
        Member member1 = new Member();
        member1.setName("spring");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("spring");

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }

}
