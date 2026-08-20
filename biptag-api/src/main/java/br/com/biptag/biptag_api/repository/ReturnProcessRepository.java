package br.com.biptag.biptag_api.repository;

import br.com.biptag.biptag_api.model.ReturnProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnProcessRepository extends JpaRepository<ReturnProcess, Long> {
}