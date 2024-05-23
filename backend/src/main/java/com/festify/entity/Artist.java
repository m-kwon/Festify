package com.festify.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "artists")
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String thumbnailUrl;

    @ManyToOne
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival;
}