package davejang.core;

import davejang.core.member.domain.Member;
import davejang.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import java.util.Optional;

@Controller
public class HomeController {
    private final MemberService memberService;

    @Autowired
    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(@CookieValue(name = "memberId", required = false) Cookie cookie, Model model) {

        if(cookie != null) {
            Long userId = Long.parseLong(cookie.getValue());
            Optional<Member> checkMember = memberService.findOne(userId);

            if (!checkMember.isEmpty()) {
                Member loginMember = checkMember.get();
                model.addAttribute("username", loginMember.getName());
            }
        }
        
        return "home";
    }
}
