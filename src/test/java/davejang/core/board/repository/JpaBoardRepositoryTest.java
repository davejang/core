package davejang.core.board.repository;

import davejang.core.board.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class JpaBoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void create(){
        Board board = new Board();
        board.setTitle("안녕하세요");
        board.setContent("반갑습니다");

        Board createBoard = boardRepository.create(board);

        Assertions.assertThat(createBoard).isEqualTo(board);

        Board board2 = new Board();
        board.setTitle("안녕?");
        board.setContent("반갑습니다");

        Board createBoard2 = boardRepository.create(board2);

        Assertions.assertThat(createBoard2).isEqualTo(board2);
    }

    @Test
    public void delete(){
        Board board = new Board();
        board.setTitle("안녕하세요");
        board.setContent("반갑습니다");

        Board createBoard = boardRepository.create(board);
        boardRepository.delete(board);

        Optional<Board> board2 = boardRepository.read(createBoard.getId());

        Assertions.assertThat(board2).isEmpty();

    }

}