package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.Orderline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderlineRepository extends JpaRepository<Orderline, Long> {
}
