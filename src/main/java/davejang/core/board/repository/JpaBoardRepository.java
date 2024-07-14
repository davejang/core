package davejang.core.board.repository;

import davejang.core.board.domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaBoardRepository implements BoardRepository{
    private final EntityManager em;
    private final JpaEntityInformation<Board, ?> entityInformation;

    @Autowired
    public JpaBoardRepository(EntityManager em){
        this.em = em;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(Board.class, em);
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
    public void delete(Board board) {

        Assert.notNull(board, "Entity must not be null!");

        if (entityInformation.isNew(board)) {
            return;
        }

        Class<?> type = ProxyUtils.getUserClass(board);
        Board checkExist = (Board)em.find(type, entityInformation.getId(board));
        if(checkExist == null) {
            return;
        }

        em.remove(em.contains(board) ? board : em.merge(board));
    }

    @Override
    public Page<Board> boardListAll(Pageable pageable) {
        String jpql = "SELECT p FROM Board p";
        long totalRows = getTotalRows();

        TypedQuery<Board> query = em.createQuery(jpql, Board.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        return new PageImpl<>(query.getResultList(), pageable, totalRows);
    }

    private long getTotalRows() {
        String countJpql = "SELECT COUNT(p) FROM Board p";
        TypedQuery<Long> query = em.createQuery(countJpql, Long.class);
        return query.getSingleResult();
    }
}
