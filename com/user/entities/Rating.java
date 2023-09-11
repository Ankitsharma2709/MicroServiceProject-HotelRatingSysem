package com.user.entities;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//this class won't be included in the database
public class Rating {
    private String ratingId;
    private String userId;
    private String hotelId;
    private String rating;
    private String remark;
    private String feedback;
    private Hotel hotel;

}
