package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.AdEntity;

public interface AdRepository extends JpaRepository<AdEntity, Long> {
}
