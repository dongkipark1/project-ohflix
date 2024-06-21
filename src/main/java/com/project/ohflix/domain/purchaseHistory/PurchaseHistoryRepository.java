package com.project.ohflix.domain.purchaseHistory;

import com.project.ohflix.domain.cardInfo.CardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {

    @Query("select p from PurchaseHistory p join fetch p.user pu where pu.id = :userId")
    Optional<List<PurchaseHistory>> findByUserId(@Param("userId") Integer userId);
}
