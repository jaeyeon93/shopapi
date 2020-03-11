package com.style.demo.repo;


import com.style.demo.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodRepository extends JpaRepository<Good, Long> {
}
