package com.github.korsejong.flight.domain.member;

import com.github.korsejong.flight.domain.journal.Journal;
import com.github.korsejong.flight.domain.path.Path;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Member
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private boolean deleted = false;
    @Column
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole = MemberRole.USER;
    @OneToMany(mappedBy = "member")
    @ToString.Exclude private List<Journal> histories = new ArrayList<Journal>();
    @OneToMany(mappedBy = "member")
    @ToString.Exclude private List<Path> paths = new ArrayList<Path>();
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Member(){}
    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
