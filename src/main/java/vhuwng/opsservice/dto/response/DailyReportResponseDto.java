package vhuwng.opsservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import vhuwng.opsservice.entity.ReportEntity;

public record DailyReportResponseDto(
        Long id,
        LocalDate reportDate,
        Integer orderCount,
        BigDecimal totalAmount,
        String currency
) {
    public static DailyReportResponseDto fromEntity(ReportEntity report) {
        return new DailyReportResponseDto(
                report.getId(),
                report.getReportDate(),
                report.getOrderCount(),
                report.getTotalAmount(),
                report.getCurrency()
        );
    }
}
