package com.github.korsejong.flight.domain.history;

import com.github.korsejong.flight.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String fromLat;
    @Column
    private String fromLng;
    @Column
    private String toLat;
    @Column
    private String toLng;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public History() {}
    public History(String fromLat, String fromLng, String toLat, String toLng){
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
    }
    public History(User user, String fromLat, String fromLng, String toLat, String toLng){
        this.user = user;
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
    }
}
