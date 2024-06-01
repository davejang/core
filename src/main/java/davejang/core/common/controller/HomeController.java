package davejang.core.common.controller;

import davejang.core.member.domain.Member;
import davejang.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class HomeController {

    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String loginPage(@CookieValue(name = "memberId", required = false) Long memberId, Model model, HttpServletResponse response) {

        if (memberId == null) {
            return "home";
        }

        Optional<Member> checkMember = memberService.findOne(memberId);
        Member loginMember = checkMember.get();
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("username", loginMember.getName());
        return "home";
    }
}
