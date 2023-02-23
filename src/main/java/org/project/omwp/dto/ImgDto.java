package org.project.omwp.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImgDto {

    private Long imgId;

    private String imgOldName;

    private String imgNewName;

}
