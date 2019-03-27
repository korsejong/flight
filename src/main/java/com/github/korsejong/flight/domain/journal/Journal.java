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
    private String lat;
    @Column
    private String lng;
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
    public Journal(String lat, String lng){
        this.lat = lat;
        this.lng = lng;
    }
    public Journal(Member member, String lat, String lng){
        this.member = member;
        this.lat = lat;
        this.lng = lng;
    }
}
