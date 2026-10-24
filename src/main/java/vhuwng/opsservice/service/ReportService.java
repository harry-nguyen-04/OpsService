package vhuwng.opsservice.service;

import java.time.LocalDate;

import vhuwng.opsservice.dto.response.DailyReportResponseDto;

public interface ReportService {
    DailyReportResponseDto getDailyReport(LocalDate date);
}
