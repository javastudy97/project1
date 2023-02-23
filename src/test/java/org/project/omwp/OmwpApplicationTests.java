package org.project.omwp;

import org.junit.jupiter.api.Test;
import org.project.omwp.repository.WishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
class OmwpApplicationTests {


	@Autowired
	private WishRepository wishRepository;


	@Test
	void contextLoads() {

//		wishRepository.findByUserId(1L, pageable);

	}

}
