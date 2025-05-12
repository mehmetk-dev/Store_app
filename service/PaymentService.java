package service;

import dao.PaymentDAO;
import model.Order;
import model.Payment;
import model.enums.PaymentType;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    public Payment save(Order order, PaymentType paymentType){

        Payment py = new Payment(order,paymentType);
        paymentDAO.save(py);
        return py;
    }
}
