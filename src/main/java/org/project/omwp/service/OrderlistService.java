package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.repository.OrderlistRepository;
import org.project.omwp.repository.WishRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderlistService {

    private final OrderlistRepository orderlistRepository;
}
