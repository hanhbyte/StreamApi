package com.company.streamApi.abstractProduct;

public class MasterProduct extends Product{
    private boolean isAvailableForLease;

    @Override
    public boolean isAvailableInStock() {
        return getRemainingAmountInStock() > 0;
    }
}
