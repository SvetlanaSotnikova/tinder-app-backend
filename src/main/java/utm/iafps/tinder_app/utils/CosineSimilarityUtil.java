package utm.iafps.tinder_app.utils;

import java.util.*;

public class CosineSimilarityUtil {
    public static double calculateSimilarity(List<String> list1, List<String> list2) {
        Set<String> allTerms = new HashSet<>();
        allTerms.addAll(list1);
        allTerms.addAll(list2);

        Map<String, Integer> vec1 = new HashMap<>();
        Map<String, Integer> vec2 = new HashMap<>();

        for (String term : allTerms) {
            vec1.put(term, Collections.frequency(list1, term));
            vec2.put(term, Collections.frequency(list2, term));
        }

        // вычисляем скалярное произведение
        double dotProduct = 0.0;
        for (String term : allTerms) {
            dotProduct += vec1.get(term) * vec2.get(term);
        }

        // длины векторов
        double magnitude1 = Math.sqrt(vec1.values().stream().mapToInt(i -> i * i).sum());
        double magnitude2 = Math.sqrt(vec2.values().stream().mapToInt(i -> i * i).sum());

        return (magnitude1 == 0 || magnitude2 == 0) ? 0.0 : dotProduct / (magnitude1 * magnitude2);
    }
}
