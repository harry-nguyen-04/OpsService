package vhuwng.opsservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import vhuwng.opsservice.dto.response.DailyReportResponseDto;
import vhuwng.opsservice.entity.ReportEntity;
import vhuwng.opsservice.middleware.exception.ResourceNotFoundException;
import vhuwng.opsservice.repository.ReportRepository;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock
    private ReportRepository reportRepository;

    private ReportServiceImpl reportService;

    @BeforeEach
    void setUp() {
        reportService = new ReportServiceImpl(reportRepository);
    }

    @Test
    void getDailyReportReturnsLatestSeededReportWhenDateIsOmitted() {
        when(reportRepository.findFirstByOrderByReportDateDesc()).thenReturn(Optional.of(report()));

        DailyReportResponseDto result = reportService.getDailyReport(null);

        assertEquals(2L, result.id());
        assertEquals(LocalDate.of(2026, 10, 25), result.reportDate());
        assertEquals(4, result.orderCount());
        assertEquals(new BigDecimal("250000.00"), result.totalAmount());
        assertEquals("VND", result.currency());
    }

    @Test
    void getDailyReportThrowsWhenDateHasNoReport() {
        LocalDate date = LocalDate.of(2026, 1, 1);
        when(reportRepository.findByReportDate(date)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> reportService.getDailyReport(date)
        );

        assertEquals("Daily report not found", exception.getMessage());
    }

    private ReportEntity report() {
        ReportEntity report = new ReportEntity();
        report.setId(2L);
        report.setReportDate(LocalDate.of(2026, 10, 25));
        report.setOrderCount(4);
        report.setTotalAmount(new BigDecimal("250000.00"));
        report.setCurrency("VND");
        return report;
    }
}
