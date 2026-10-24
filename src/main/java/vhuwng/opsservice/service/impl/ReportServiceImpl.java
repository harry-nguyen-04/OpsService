package vhuwng.opsservice.service.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import vhuwng.opsservice.dto.response.DailyReportResponseDto;
import vhuwng.opsservice.entity.ReportEntity;
import vhuwng.opsservice.middleware.exception.ResourceNotFoundException;
import vhuwng.opsservice.repository.ReportRepository;
import vhuwng.opsservice.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public DailyReportResponseDto getDailyReport(LocalDate date) {
        ReportEntity report = (date == null
                ? reportRepository.findFirstByOrderByReportDateDesc()
                : reportRepository.findByReportDate(date))
                .orElseThrow(() -> new ResourceNotFoundException("Daily report not found"));
        return DailyReportResponseDto.fromEntity(report);
    }
}
