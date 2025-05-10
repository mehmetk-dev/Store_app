package model;

import model.enums.PaymentType;
import java.math.BigDecimal;

public class Payment {

    private Order order;
    private PaymentType paymentType;
    private BigDecimal amount;

    public Payment(Order order, PaymentType paymentType, BigDecimal amount) {
        this.order = order;
        this.paymentType = paymentType;
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
