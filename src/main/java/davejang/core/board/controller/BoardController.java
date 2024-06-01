package davejang.core.board.controller;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    @GetMapping(value = "/mainPage")
    public String getMainPage(HttpServletRequest request, Model model) {

        return "board/mainPage";
    }

}
