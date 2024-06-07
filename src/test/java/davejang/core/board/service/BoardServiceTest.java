package davejang.core.board.service;

import davejang.core.board.domain.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    @Transactional
    void createTestBoard() {
        for(int i=1; i<500; i++) {
            Board testBoard = new Board();
            testBoard.setTitle(String.format("%d번째 테스트 게시글", i));
            testBoard.setContent("테스트 게시글 입니다");
            testBoard.setWriter("admin");
            testBoard.setCreateDate(LocalDate.now());
            boardService.boardCreate(testBoard);
        }
    }

}