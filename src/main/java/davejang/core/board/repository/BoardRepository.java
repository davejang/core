package davejang.core.board.repository;

import davejang.core.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board create(Board board);
    Optional<Board> read(Long boardId);
    Board update(Board board);
    Board delete(Board board);
    Page<Board> boardListAll(Pageable pageable);
}
