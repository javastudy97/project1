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
public class OrderlistDto {

    private Long orderlistId;

    private int orderlistCount;

    private int orderlistPrice;

    private LocalDateTime orderlistDate;

    private int orderlistStatus;

}
