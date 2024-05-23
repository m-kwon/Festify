package com.festify.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "festivals")
@Data
public class Festival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private String startDate;
    private String endDate;
    private String thumbnailUrl;
    private String officialUrl;

    @OneToMany(mappedBy = "festival", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Artist> lineup;
}