package com.dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.domain.Papel;
import com.dev.domain.Status;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long> {
	
	List<Papel> findByStatus(Status status);

}
