package utm.iafps.tinder_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import utm.iafps.tinder_app.utils.Country;
import utm.iafps.tinder_app.utils.Gender;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Country country; // для DBSCAN
    private String city; // для DBSCAN

    @ElementCollection
    @CollectionTable(name = "profile_interests", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "interests")
    private List<String> interests;

    @ElementCollection
    @CollectionTable(name = "profile_hobbies", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "hobbies")
    private List<String> hobbies;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "avatar_url")
    private String avatarUrl;

}
