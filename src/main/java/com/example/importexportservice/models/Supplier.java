package com.example.importexportservice.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "supplier_country")
    private String country ;

    @Column(name = "supplier_region")
    private String region ;
    @ElementCollection
    @Column(name = "Product_id")
    private List<Long> productIds;

    public Supplier(Long supplierId, String supplierName, String country, String region, List<Long> productIds) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.country = country;
        this.region = region;
        this.productIds = productIds;
    }



    public Supplier() {

    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }



    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

} 