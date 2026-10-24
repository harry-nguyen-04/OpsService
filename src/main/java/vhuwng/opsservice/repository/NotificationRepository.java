package vhuwng.opsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vhuwng.opsservice.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}
