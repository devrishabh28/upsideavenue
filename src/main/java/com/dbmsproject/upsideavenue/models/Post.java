package com.dbmsproject.upsideavenue.models;

import java.sql.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder.Default;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID postId;

    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    private User agentId;

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property propertyId;

    @Column(nullable = false)
    private Date postDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Mode mode;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Default
    private PostStatus postStatus = PostStatus.AVAILABLE;

    @Column(nullable = false)
    private String description;

    public boolean isRent() {
        return mode == Mode.RENT;
    }

}
