package davejang.core.member.service;

import davejang.core.member.domain.Member;
import davejang.core.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    public Member login(String name, String pw) {
        Optional<Member> checkUser = memberRepository.findByName(name);

        if(checkUser.isEmpty()) {
            return null;
        }

        Member loginMember = checkUser.get();

        if(loginMember.getPw().equals(pw)) {
            return loginMember;
        }
        else {
            return null;
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                } );
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findByName(String memberName) {
        return memberRepository.findByName(memberName);
    }
}
