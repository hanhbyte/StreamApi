package com.company.streamApi.interFaceDemo;

public class Client {
    private PaymentProcessor paymentProcessor;

    {
        paymentProcessor = new PayPalPaymenProcessor();
    }

    public void checkout(PaymentData payment) {
        // ... some methods calls goes here
        paymentProcessor.processPayment(payment);
        PaymentProcessor.someStaticMethod(); // demo of static method invocation
    }

    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
}
