package com.github.korsejong.flight.domain.journal;

import com.github.korsejong.flight.domain.member.Member;
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
public class Journal {
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
    @Column
    private String text;
    @Column
    private boolean deleted = false;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp updatedAt;

    public Journal() {}
    public Journal(String fromLat, String fromLng, String toLat, String toLng){
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
    }
    public Journal(Member member, String fromLat, String fromLng, String toLat, String toLng){
        this.member = member;
        this.fromLat = fromLat;
        this.fromLng = fromLng;
        this.toLat = toLat;
        this.toLng = toLng;
    }
}
