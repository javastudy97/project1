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
public class ProductDto {

    private Long productId;

    private String productType;

    private String productName;

    private String productDesc;

    private int productPrice;

    private LocalDateTime productCreate;

    private int reviewCount;



}
