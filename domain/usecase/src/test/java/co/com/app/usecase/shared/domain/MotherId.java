package co.com.app.usecase.shared.domain;

import java.util.UUID;

public class MotherId {

    public static String random(){
         return UUID.randomUUID().toString();
    }
}
