package br.com.biptag.biptag_api.repository;

import br.com.biptag.biptag_api.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByStatus(String status);

    List<Alert> findAllByStatus(String status);
}