package at.htl.zoomanagement.repository;

import at.htl.zoomanagement.entity.Animal;
import at.htl.zoomanagement.entity.Enclosure;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class AnimalRepository implements PanacheRepository<Animal> {

    public List<Animal> getAll() {
        return listAll();
    }

    public Animal findById(long id) {
        return find("id", id).firstResult();
    }

    @Transactional
    public long add(Animal animal) {
        Enclosure enclosure = getEntityManager().createQuery("select e from Enclosure e where e.name = ?1", Enclosure.class)
                .setParameter(1, animal.getEnclosure().getName())
                .getSingleResult();
        animal.setEnclosure(enclosure);
        persist(animal);
        return animal.getId();
    }

    @Transactional
    public long modify(long id, Animal animal) {
        Animal toModify = findById(id);
        toModify.setBirthdate(animal.getBirthdate());
        toModify.setName(animal.getName());
        toModify.setSpecies(animal.getSpecies());
        toModify.setEnclosure(animal.getEnclosure());
        return toModify.getId();
    }

    @Transactional
    public boolean delete(long id) {
        Animal del = findById(id);
        if (del == null) {
            return false;
        }
        delete(del);
        return true;
    }

    @Transactional
    public void reset() {
        for (Animal animal : getAll()) {
            delete(animal.getId());
        }
    }
}
