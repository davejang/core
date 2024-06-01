package davejang.core.board.repository;

import davejang.core.board.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board create(Board board);
    Optional<Board> read(Long boardId);
    Board update(Board board);
    Board delete(Board board);
    List<Board> boardListAll();
    void increaseViewCount(Long boardId);
}
