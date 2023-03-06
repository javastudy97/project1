package org.project.omwp;

import org.junit.jupiter.api.Test;
import org.project.omwp.dto.ImgDto;
import org.project.omwp.dto.MemberDto;
import org.project.omwp.entity.ImgEntity;
import org.project.omwp.entity.ProductEntity;
import org.project.omwp.repository.ImgRepository;
import org.project.omwp.repository.ProductRepository;
import org.project.omwp.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@SpringBootTest
class OmwpApplicationTests {

	@Autowired
	private ImgRepository imgRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private WishRepository wishRepository;


	@Test
	void contextLoads() {

//		wishRepository.findByUserId(1L, pageable);
//		Optional<ProductEntity> productEntity=productRepository.findById(1L);
//		if(!productEntity.isPresent()){
//			System.out.println("no");
//		}
//			System.out.println("Yes");

		Optional<ImgEntity> imgEntity= imgRepository.findByImageNewNameOnProject(1L);
		if(!imgEntity.isPresent()){
			System.out.println("no");
		}
		System.out.println("Yes");

		ImgDto imgDto=ImgDto.toImgDto(imgEntity.get());
	System.out.println(imgDto.getImgNewName()+" <<");

	}

}
