package com.myproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    public Product() {
    }

    public static Builder getBuilder() {
        return new Product().new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            Product.this.id = id;
            return Builder.this;
        }

        public Builder setTitle(String title) {
            Product.this.title = title;
            return Builder.this;
        }

        public Builder setDescription(String description) {
            Product.this.description = description;
            return Builder.this;
        }

        public Builder setPrice(Double price) {
            Product.this.price = price;
            return Builder.this;
        }

        public Product build() {
            return Product.this;
        }
    }
}
