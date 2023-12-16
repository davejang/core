package davejang.core.member.controller;

import davejang.core.member.domain.Member;
import davejang.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String loginPage(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {

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

    @GetMapping(value = "/join")
    public String createForm() {
        return "createMemberForm";
    }

    @PostMapping(value = "/join")
    public String join(MemberForm form, Member member) {
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPw(form.getPw());

        memberService.join(member);

        return "redirect:/";
    }

    @PostMapping(value = "/login")
    public String login(@CookieValue(name = "memberId", required = false) Long memberId, Model model, MemberForm form, HttpServletResponse response) {

        Member member = memberService.login(form.getName(), form.getPw());

        if(member == null) {
            return "home";
        }

        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(idCookie);

        model.addAttribute("username", member.getName());

        return "loginSuccess";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response,"memberId");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}