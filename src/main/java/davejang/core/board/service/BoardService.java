package davejang.core.board.service;

import davejang.core.board.domain.Board;
import davejang.core.board.repository.BoardRepository;
import davejang.core.board.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 15, sort);
        return boardRepository.boardListAll(pageable);
    }

    public Optional<Board> boardView(Long id) {
        return boardRepository.read(id);
    }

    public void boardDelete(Long id) {
        Optional<Board> deleteBoard = boardRepository.read(id);
        if(deleteBoard.isPresent()) {
            boardRepository.delete(deleteBoard.get());
        }
    }

}
