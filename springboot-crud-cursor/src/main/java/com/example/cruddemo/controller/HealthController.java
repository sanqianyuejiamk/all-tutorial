package com.example.cruddemo.controller;

import com.example.cruddemo.config.HikariConfigMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    private final HikariConfigMonitor hikariConfigMonitor;

    @Autowired
    public HealthController(HikariConfigMonitor hikariConfigMonitor) {
        this.hikariConfigMonitor = hikariConfigMonitor;
    }

    /**
     * 获取连接池状态
     * GET /api/health/pool
     */
    @GetMapping("/pool")
    public ResponseEntity<Map<String, Object>> getPoolStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("status", hikariConfigMonitor.getPoolStatus());
        return ResponseEntity.ok(status);
    }
}

