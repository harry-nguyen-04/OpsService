package vhuwng.opsservice.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vhuwng.opsservice.entity.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
    Optional<ReportEntity> findByReportDate(LocalDate reportDate);

    Optional<ReportEntity> findFirstByOrderByReportDateDesc();
}
