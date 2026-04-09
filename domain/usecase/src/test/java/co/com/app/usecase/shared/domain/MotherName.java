package co.com.app.usecase.shared.domain;

import java.util.UUID;

public class MotherName {

    public static String random() {

          return "Jhon"+ UUID.randomUUID().toString();
    }
}
