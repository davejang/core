package davejang.core.board.repository;

import davejang.core.board.domain.Board;

import java.util.List;

public interface BoardRepository {

    Board create(Board board);
    Board read(int boardId);
    Board update(Board board);
    Board delete(Board board);
    List<Board> boardListAll();
    void increaseViewCount(int boardId);
}
