package davejang.core.board.service;

import davejang.core.board.domain.Board;
import davejang.core.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public void boardCreate(Board board) {
        boardRepository.create(board);
    }

    public Page<Board> boardList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return boardRepository.boardListAll(pageable);
    }


}
