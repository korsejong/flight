package com.github.korsejong.flight.domain.user;

import com.github.korsejong.flight.domain.history.History;
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
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String email;
    @Column
    private String password;
    @OneToMany(mappedBy = "user") @ToString.Exclude
    private List<History> histories = new ArrayList<History>();
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public User(){}
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
