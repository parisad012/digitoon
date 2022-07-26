package com.digitoon.batman.room.rating;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.digitoon.batman.room.detail.DetailRoom;

import java.util.List;


public class DetailWithRatings {

    @Embedded
    public DetailRoom detail;
    @Relation(
            parentColumn = "id_detail",
            entityColumn = "id_rate"
    )
    public List<Rating> ratings;

    public DetailWithRatings(DetailRoom detail, List<Rating> ratings) {
        this.detail=detail;
        this.ratings=ratings;
    }

}