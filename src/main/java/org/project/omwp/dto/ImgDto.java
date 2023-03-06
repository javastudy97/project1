package org.project.omwp.dto;

import lombok.*;
import org.project.omwp.entity.ImgEntity;

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

    public static ImgDto toImgDto(ImgEntity imgEntity){
        ImgDto imgDto = new ImgDto();
        imgDto.setImgNewName(imgEntity.getImgNewName());

        return imgDto;

    }
}
