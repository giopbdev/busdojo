package co.com.app.usecase.shared.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MotherReviewUrl {
    public static List<String> random() {

        return new ArrayList<String>(List.of("review1"+ UUID.randomUUID().toString(),
                "review2"+ UUID.randomUUID().toString() ));
    }
}
