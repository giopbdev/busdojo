package co.com.app.usecase.shared.domain;

import java.util.random.RandomGenerator;

public class MotherPrice {

    public static Double random() {
        return RandomGenerator.getDefault()
                .nextDouble(0.0, 100.0);
    }
}
