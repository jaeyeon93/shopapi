package com.example.demo.repo;

import com.example.demo.domain.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptionRepository extends JpaRepository<Option, Integer> {
    Optional<Option> findByOptionId(int optionId);
}
