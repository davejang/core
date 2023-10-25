package davejang.core.member.repository;

import davejang.core.member.domain.Member;
import davejang.core.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class JpaMemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void save() {
        Member member = new Member();
        member.setPw("abc");
        member.setEmail("spring@kyungseok.com");
        member.setName("spring123");
        member.setType("Admin");

        memberRepository.save(member);

        Member result = memberRepository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setPw("abc");
        member.setEmail("spring@kyungseok.com");
        member.setName("spring123");

        memberRepository.save(member);

        Member result = memberRepository.findByName("spring123").get();
        Assertions.assertThat(member).isEqualTo(result);
    }
}
