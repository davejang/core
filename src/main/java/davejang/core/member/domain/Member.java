package davejang.core.member.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "pw")
    private String pw;
    @Column(name = "name" , unique = true)
    private String name;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "activation")
    private Boolean activation;
    @Column(name = "deactivate_date")
    private Date deactivateDate;
    @Column(name = "type")
    private String type;
}
