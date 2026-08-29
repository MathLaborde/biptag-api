package br.com.biptag.biptag_api.repository;

import br.com.biptag.biptag_api.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByReturnProcessId(Long returnProcessId);
}
