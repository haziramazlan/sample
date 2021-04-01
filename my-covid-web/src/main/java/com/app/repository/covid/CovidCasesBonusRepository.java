package com.app.repository.covid;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesBonusEntity;

public interface CovidCasesBonusRepository extends JpaRepository<CovidCasesBonusEntity, Long> {
	
	@Transactional
	@Modifying
	@Query("DELETE FROM CovidCasesBonusEntity d WHERE d.description = :bonus")
	int deleteBonusWithCondition(String bonus);
	
}
