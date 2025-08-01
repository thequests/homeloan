

package com.oracle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.oracle.entities.*;

@Repository
public interface EMIScheduleRepository extends JpaRepository<EMISchedule, Long> {
	List<EMISchedule> findByAccountNumber(String accountNumber);
    // You can add custom query methods here if needed
}
