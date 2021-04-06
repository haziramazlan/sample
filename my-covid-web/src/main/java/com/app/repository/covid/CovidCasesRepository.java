package com.app.repository.covid;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesAreaEntity;

public interface CovidCasesRepository extends JpaRepository<CovidCasesAreaEntity, UUID>  {
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast2Records();
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQL();
	
	@Query("SELECT c FROM CovidCasesAreaEntity AS c order by date desc")
	List<CovidCasesAreaEntity> listLast5RecordsHQLWithSize(Pageable pageable);
}