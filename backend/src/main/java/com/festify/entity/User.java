package com.festify.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String id; // Firebase UID
    private String email;
    private String displayName;
}