package org.project.omwp.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
//@Builder
@Entity
@Table(name = "img")
public class ImgEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long imgId;

//  원본파일명
    @Column(name = "img_old_name", nullable = false)
    private String imgOldName;

//  저장파일명
    @Column(name = "img_new_name", nullable = false)
    private String imgNewName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

}
