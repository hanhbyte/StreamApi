package com.company.streamApi.inheiritance;

public class Phone extends Product{
    public Phone() {
        super("Nokia");
        System.out.println("Some code");
    }

    public void ring() {
        System.out.println("Ring!");
    }

    @Override
    public Product[] listVariants() {
        throw new UnsupportedOperationException();
    }

    public int calculateAmountOfVariants() {
        return super.listVariants().length;
    }
}
