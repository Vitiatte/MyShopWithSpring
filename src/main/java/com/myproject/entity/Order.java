package com.myproject.entity;

import com.myproject.entity.enums.OrderStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "`order`")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "check_code")
    private String checkCode;

    @Column(name = "user_id")
    private long userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();

    public Order() {
    }

    public static Builder getBuilder() {
        return new Order().new Builder();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            Order.this.id = id;
            return Builder.this;
        }

        public Builder setEmail(String email) {
            Order.this.email = email;
            return Builder.this;
        }

        public Builder setAddress(String address) {
            Order.this.address = address;
            return Builder.this;
        }

        public Builder setCheckCode(String checkCode) {
            Order.this.checkCode = checkCode;
            return Builder.this;
        }

        public Builder setUserId(Long id) {
            Order.this.userId = id;
            return Builder.this;
        }

        public Builder setStatus(OrderStatus status) {
            Order.this.status = status;
            return Builder.this;
        }

        public Builder setProductList(List<Product> productList) {
            for (Product product : productList) {
                Order.this.productList.add(product);
            }
            return Builder.this;
        }

        public Order build() {
            return Order.this;
        }
    }
}
