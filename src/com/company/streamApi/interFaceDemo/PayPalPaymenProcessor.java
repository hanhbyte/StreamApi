package com.company.streamApi.interFaceDemo;

public class PayPalPaymenProcessor implements PaymentProcessor{
    @Override
    public void processPayment(PaymentData payment) {
        /* PayPalForm paymentForm = PayPalApi.getPaymentFormByAppId(APP_ID);
         * fillPaymentForm(paymentForm, payment);
         * paymentForm.submit();
         * ...
         */

    }
}
