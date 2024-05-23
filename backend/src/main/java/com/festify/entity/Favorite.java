package com.festify.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorites")
@IdClass(FavoriteId.class)
@Data
public class Favorite {
    @Id
    private String userId;
    @Id
    private Long festivalId;
}