package com.example.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.LoanApplication;

@Repository
public interface LoanApplicationStatusRepository extends JpaRepository<LoanApplication, Integer>{

	Optional<LoanApplication> findByCustomerId(int customerId);

    /**
     * Finds all applications with a specific status.
     * @param status The status to search for (true for approved, false for rejected).
     * @return A list of applications matching the status.
     */
    List<LoanApplication> findByApplicationStatus(Boolean status);

    /**
     * Counts the number of applications submitted between two dates.
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     * @return The number of applications.
     */
    @Query("SELECT count(l) FROM LoanApplication l WHERE l.applicationDate >= :startDate AND l.applicationDate < :endDate")
    long countByApplicationDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	
}
