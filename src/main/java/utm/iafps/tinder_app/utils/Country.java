package utm.iafps.tinder_app.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum Country {
    MOLDOVA(List.of("Chisinau", "Balti", "Cahul")),
    ROMANIA(List.of("Bucharest", "Cluj-Napoca", "Iasi")),
    GERMANY(List.of("Berlin", "Munich", "Hamburg")),
    OTHER(List.of());


    private final List<String> cities;

    Country(List<String> cities) {
        this.cities = cities;
    }

}
