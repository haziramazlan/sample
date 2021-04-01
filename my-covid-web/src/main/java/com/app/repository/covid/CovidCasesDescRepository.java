package com.app.repository.covid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository extends JpaRepository<CovidCasesDescEntity, Long>  {

	@Transactional
	@Modifying
	@Query("DELETE FROM CovidCasesDescEntity d WHERE d.description = :desc")
	int deleteDescWithCondition(String desc);
	
	@Query("SELECT description FROM CovidCasesDescEntity d GROUP BY description HAVING COUNT(*)>1")
	List<String> findDuplicate();

}
