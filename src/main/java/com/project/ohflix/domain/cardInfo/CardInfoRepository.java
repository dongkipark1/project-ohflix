package com.project.ohflix.domain.cardInfo;

import com.project.ohflix.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardInfoRepository extends JpaRepository<CardInfo, Integer> {

    @Query("""
             select c from CardInfo c join fetch User u where c.user.id = :userId
             """)
    Optional<CardInfo> findUserInfo(@Param("userId") Integer userId);
}
