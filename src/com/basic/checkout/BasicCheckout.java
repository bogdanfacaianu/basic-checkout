package com.basic.checkout;

import com.basic.checkout.simulator.CheckoutSimulator;
import com.basic.checkout.simulator.Simulator;

public class BasicCheckout {

    public static void main(String[] args) {
        Simulator simulator = new CheckoutSimulator();
        simulator.startSimulator();
    }
}
