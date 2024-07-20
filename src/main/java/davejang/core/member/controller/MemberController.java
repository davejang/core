package davejang.core.member.controller;

import davejang.core.member.domain.Member;
import davejang.core.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String login(Long memberId,
                        Model model,
                        RedirectAttributes redirectAttributes,
                        MemberForm form,
                        HttpSession session) {

        Member member = memberService.login(form.getName(), form.getPw());

        if(member == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid login information.");
            return "redirect:/";
        }

        session.setAttribute("username", member.getName());

        return "redirect:/board/mainPage";
    }

    @PostMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}