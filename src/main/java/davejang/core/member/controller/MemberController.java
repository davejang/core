package davejang.core.member.controller;

import davejang.core.member.domain.Member;
import davejang.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/members")
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping(value = "/join")
    public String createForm() {
        return "members/createMemberForm";
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

        Cookie authCookie = new Cookie("auth", String.valueOf(member.getName()));
        authCookie.setPath("/");
        response.addCookie(authCookie);
        model.addAttribute("username", member.getName());

        return "board/mainPage";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletResponse response) {
        expireCookie(response,"auth");
        System.out.println("만료");
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}