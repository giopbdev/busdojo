package co.com.app.usecase.shared.domain;

import java.util.ArrayList;
import java.util.List;

public class MotherImagesUrl {
    public static List<String> random() {
        return new ArrayList<>(List.of("http://url.com", "http://url2.com"));
    }
}
