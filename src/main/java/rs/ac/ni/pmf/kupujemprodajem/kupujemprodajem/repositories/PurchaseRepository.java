package rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.kupujemprodajem.kupujemprodajem.model.entity.PurchaseEntity;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity,Long> {
}
