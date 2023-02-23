package org.project.omwp.service;

import lombok.RequiredArgsConstructor;
import org.project.omwp.repository.OrderlistRepository;
import org.project.omwp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
}
