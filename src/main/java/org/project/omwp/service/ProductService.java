package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.repository.ImgRepository;
import org.project.omwp.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ImgRepository imgRepository;
}
