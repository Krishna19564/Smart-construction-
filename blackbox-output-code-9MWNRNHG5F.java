package com.construction.controller;

import com.construction.model.Delivery;
import com.construction.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/deliveries")
@CrossOrigin(origins = "http://localhost:8080")
public class DeliveryController {
    
    @Autowired
    private DeliveryRepository deliveryRepository;
    
    @GetMapping
    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }
    
    @PostMapping("/schedule")
    public Delivery scheduleDelivery(@RequestBody Delivery delivery) {
        delivery.setStatus("SCHEDULED");
        return deliveryRepository.save(delivery);
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        return deliveryRepository.findById(id)
                .map(delivery -> {
                    delivery.setStatus(statusUpdate.get("status"));
                    if ("DELIVERED".equals(statusUpdate.get("status"))) {
                        delivery.setActualTime(LocalDateTime.now());
                    }
                    deliveryRepository.save(delivery);
                    return ResponseEntity.ok(delivery);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/dashboard")
    public Map<String, Object> getDashboardStats() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        long pending = deliveries.stream().filter(d -> "PENDING".equals(d.getStatus())).count();
        long scheduled = deliveries.stream().filter(d -> "SCHEDULED".equals(d.getStatus())).count();
        long inTransit = deliveries.stream().filter(d -> "IN_TRANSIT".equals(d.getStatus())).count();
        long delivered = deliveries.stream().filter(d -> "DELIVERED".equals(d.getStatus())).count();
        
        return Map.of(
            "total", deliveries.size(),
            "pending", pending,
            "scheduled", scheduled,
            "inTransit", inTransit,
            "delivered", delivered
        );
    }
}