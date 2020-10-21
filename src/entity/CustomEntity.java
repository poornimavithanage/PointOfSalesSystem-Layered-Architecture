package entity;

import java.math.BigDecimal;
import java.sql.Date;

public class CustomEntity implements SuperEntity {
    private String orderId;
    private String customerName;
    private Date orderDate;
    private String customerId;
    private BigDecimal total;

    public CustomEntity() {
    }

    public CustomEntity(String orderId, String customerName, Date orderDate, String customerId, BigDecimal total) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.orderDate = orderDate;
        this.customerId = customerId;
        this.total = total;
    }

    public CustomEntity(String orderId, String customerName, Date orderDate) {
        this.setOrderId(orderId);
        this.setCustomerName(customerName);
        this.setOrderDate(orderDate);
    }

    public CustomEntity(String customerId, String customerName, String orderId) {
        this.setOrderId(orderId);
        this.setCustomerName(customerName);
        this.setCustomerId(customerId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "CustomEntity{" +
                "orderId='" + getOrderId() + '\'' +
                ", customerName='" + getCustomerName() + '\'' +
                ", orderDate=" + getOrderDate() +
                '}';
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
