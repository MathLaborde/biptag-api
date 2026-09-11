package br.com.biptag.biptag_api.repository;

import br.com.biptag.biptag_api.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i LEFT JOIN FETCH i.user WHERE i.id = :id")
    Optional<Item> findByIdWithUser(@Param("id") Long id);

    List<Item> findByUserId(UUID userId);
}
