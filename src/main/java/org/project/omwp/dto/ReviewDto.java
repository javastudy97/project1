package org.project.omwp.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long reviewId;

    private String reviewContent;

    private LocalDateTime reviewDate;

}
