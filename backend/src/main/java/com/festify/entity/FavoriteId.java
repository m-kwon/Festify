package com.festify.model;

import java.io.Serializable;
import java.util.Objects;

public class FavoriteId implements Serializable {
    private String userId;
    private Long festivalId;

    public FavoriteId() {}

    public FavoriteId(String userId, Long festivalId) {
        this.userId = userId;
        this.festivalId = festivalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId that = (FavoriteId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(festivalId, that.festivalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, festivalId);
    }
}