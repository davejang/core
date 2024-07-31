package davejang.core.board.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "board_id")
    private Long boardId;
    @Column(name = "writer")
    private String writer;
    @Column(name = "parentId")
    private Long parentId;
    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;
    @Column(name = "content")
    private String content;
}

