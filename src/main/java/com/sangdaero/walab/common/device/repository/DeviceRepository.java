package com.sangdaero.walab.common.device.repository;

import com.sangdaero.walab.common.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
