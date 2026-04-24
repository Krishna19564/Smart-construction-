package com.construction.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String materialType;
    private String supplierName;
    private String vehicleNumber;
    private String destinationSite;
    private LocalDateTime scheduledTime;
    private LocalDateTime actualTime;
    private String status; // PENDING, SCHEDULED, IN_TRANSIT, DELIVERED, DELAYED
    private double quantity;
    private String gpsLocation;
    
    // Constructors
    public Delivery() {}
    
    public Delivery(String materialType, String supplierName, String vehicleNumber, 
                   String destinationSite, LocalDateTime scheduledTime, double quantity) {
        this.materialType = materialType;
        this.supplierName = supplierName;
        this.vehicleNumber = vehicleNumber;
        this.destinationSite = destinationSite;
        this.scheduledTime = scheduledTime;
        this.status = "PENDING";
        this.quantity = quantity;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getMaterialType() { return materialType; }
    public void setMaterialType(String materialType) { this.materialType = materialType; }
    
    public String getSupplierName() { return supplierName; }
    public void setSupplierName(String supplierName) { this.supplierName = supplierName; }
    
    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }
    
    public String getDestinationSite() { return destinationSite; }
    public void setDestinationSite(String destinationSite) { this.destinationSite = destinationSite; }
    
    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(LocalDateTime scheduledTime) { this.scheduledTime = scheduledTime; }
    
    public LocalDateTime getActualTime() { return actualTime; }
    public void setActualTime(LocalDateTime actualTime) { this.actualTime = actualTime; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }
    
    public String getGpsLocation() { return gpsLocation; }
    public void setGpsLocation(String gpsLocation) { this.gpsLocation = gpsLocation; }
}