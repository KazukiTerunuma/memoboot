package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer> {
	@Query("SELECT x FROM Memo x ORDER BY x.title, x.text")
	List<Memo> findAllOrderByName();

	@Query("SELECT x FROM Memo x ORDER BY x.title, x.text")
	Page<Memo> findAllOrderByName(Pageable pageable);
}