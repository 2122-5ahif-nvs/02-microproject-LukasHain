package at.htl.zoomanagement.entity;

import at.htl.zoomanagement.repository.LocalDateAdapter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@Entity
@XmlRootElement
@Schema(description = "Several animals in the zoo")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Schema(required = true)
    private String name;
    @XmlJavaTypeAdapter(type=LocalDate.class, value= LocalDateAdapter.class)
    private LocalDate birthdate;
    @Schema(required = true)
    private String species;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Enclosure enclosure;

    public Animal() {
        name = "";
        birthdate = LocalDate.of(1, 1, 1);
        species = "";
        enclosure = new Enclosure();
    }

    public Animal(String name) {
        this();
        this.name = name;
    }

    public Animal(String name, Enclosure enclosure) {
        this();
        this.name = name;
        this.enclosure = enclosure;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }
}
