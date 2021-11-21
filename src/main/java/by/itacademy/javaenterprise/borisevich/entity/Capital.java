package by.itacademy.javaenterprise.borisevich.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Capital {
    @Id
    @Column(name = "country_id")
    private Long countryId;
    @Column(nullable = false)
    private String name;

    @OneToOne(mappedBy = "capital")
    private Country country;

    public Capital(Long countryId, String name) {
        this.countryId = countryId;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Capital{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                '}';
    }
}
