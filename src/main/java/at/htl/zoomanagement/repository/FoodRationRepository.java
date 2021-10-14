package at.htl.zoomanagement.repository;

import at.htl.zoomanagement.entity.FoodRation;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FoodRationRepository implements PanacheRepository<FoodRation> {

    public List<FoodRation> getAll() {
        return listAll();
    }

    public List<FoodRation> getFoodRationsForSpecies(String species) {
        TypedQuery<FoodRation> query = getEntityManager().createQuery("select fr from FoodRation fr where fr.animal.species = ?1", FoodRation.class);
        return query.setParameter(1, species).getResultList();
    }

    public FoodRation findById(long id) {
        return find("id", id).firstResult();
    }

    @Transactional
    public long add(FoodRation foodRation) {
        persist(foodRation);
        return foodRation.getId();
    }

    @Transactional
    public long modify(long id, FoodRation foodRation) {
        FoodRation toModify = findById(id);
        toModify.setAnimal(foodRation.getAnimal());
        toModify.setFood(foodRation.getFood());
        toModify.setDate(foodRation.getDate());
        toModify.setAmount(foodRation.getAmount());
        return toModify.getId();
    }

    @Transactional
    public boolean delete(long id) {
        FoodRation del = findById(id);
        if (del == null) {
            return false;
        }
        delete(del);
        return true;
    }

    @Transactional
    public void reset() {
        for (FoodRation foodRation : getAll()) {
            delete(foodRation.getId());
        }
    }
}
