package com.app.repository.covid;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CovidCasesBonusEntity;

public interface CovidCasesBonusRepository extends JpaRepository<CovidCasesBonusEntity, Long> {

}
