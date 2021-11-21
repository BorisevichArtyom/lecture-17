package by.itacademy.javaenterprise.borisevich.entity;

import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Country  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long population;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "country_id")
    private Capital capital;

    public Country(String name, Long population, Capital capital) {
        this.name = name;
        this.population = population;
        this.capital = capital;
    }

    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
