package davejang.core.board.repository;

import davejang.core.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaBoardRepository implements BoardRepository{
    private final EntityManager em;

    @Autowired
    public JpaBoardRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Board create(Board board) {
        em.persist(board);
        return board;
    }

    @Override
    public Optional<Board> read(Long id) {
        Board board = em.find(Board.class, id);
        return Optional.ofNullable(board);
    }

    @Override
    public Board update(Board board) {
        return null;
    }

    @Override
    public Board delete(Board board) {
        return null;
    }

    @Override
    public List<Board> boardListAll() {
        return em.createQuery("select m from Board m", Board.class)
                .getResultList();
    }

    @Override
    public void increaseViewCount(Long id) {

    }
}
