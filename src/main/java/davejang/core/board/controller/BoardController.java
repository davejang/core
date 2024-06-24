package davejang.core.board.controller;

import davejang.core.board.domain.Board;
import davejang.core.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = "/mainPage")
    public String getMainPage(HttpServletRequest request, Model model, @RequestParam(value="page", defaultValue = "0") int page) {

        Page<Board> boardList = boardService.boardList(page);
        model.addAttribute("boardList", boardList);

        return "board/mainPage";
    }

    @GetMapping(value = "/view")
    public String viewBoardContent(Model model, Long id) {
        model.addAttribute("board", boardService.boardView(id).get());

        return "board/boardContent";
    }

    @GetMapping(value = "/createBoardForm")
    public String getCreateBoardFormPage(HttpServletRequest request, Model model) {

        return "board/boardForm";
    }

    @PostMapping(value = "/createBoard")
    public String createBoard(HttpServletRequest request, BoardForm boardForm, Board board) {
        HttpSession session = request.getSession(false);

        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getTitle());
        String writer = (String)session.getAttribute("username");
        board.setWriter(writer);
        board.setCreateDate(LocalDate.now());
        boardService.boardCreate(board);

        return "redirect:/board/mainPage";
    }

}
