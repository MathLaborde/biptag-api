package br.com.biptag.biptag_api.repository;

import br.com.biptag.biptag_api.model.FoundReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoundReportRepository extends JpaRepository<FoundReport, Long> {

    @Query("SELECT fa FROM FoundReport fa LEFT JOIN FETCH fa.finder WHERE fa.alertId = :alertId")
    Optional<FoundReport> findByAlertId(@Param("alertId") Long alertId);
}