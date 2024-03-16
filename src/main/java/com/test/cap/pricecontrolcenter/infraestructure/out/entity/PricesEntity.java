package com.test.cap.pricecontrolcenter.infraestructure.out.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
;

@Entity
@Table(name = "PRICES")
public class PricesEntity {

    private Long id;
    private Integer BrandId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer priceList;
    private Integer productId;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return this.id;
    }

    @Column(name = "BRAND_ID")
    public Integer getBrandId() {
        return this.BrandId;
    }

    @Column(name = "START_DATE")
    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    @Column(name = "END_DATE")
    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    @Column(name = "PRICE_LIST")
    public Integer getPriceList() {
        return this.priceList;
    }

    @Column(name = "PRODUCT_ID")
    public Integer getProductId() {
        return this.productId;
    }

    @Column(name = "PRIORITY")
    public Integer getPriority() {
        return this.priority;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return this.price;
    }

    @Column(name = "CURRENCY")
    public String getCurrency() {
        return this.currency;
    }

    @Column(name = "VERSION")
    public Long getVersion() {
        return this.version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBrandId(Integer brandId) {
        this.BrandId = brandId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPriceList(Integer priceList) {
        this.priceList = priceList;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
