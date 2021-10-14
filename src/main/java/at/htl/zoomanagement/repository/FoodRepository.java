package at.htl.zoomanagement.repository;

import at.htl.zoomanagement.entity.Food;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class FoodRepository implements PanacheRepository<Food> {
    @Inject
    EntityManager em;

    public List<Food> getAll() {
        return listAll();
    }

    public Food findById(long id) {
        return find("id", id).firstResult();
    }

    @Transactional
    public long add(Food food) {
        persist(food);
        return food.getId();
    }

    @Transactional
    public long modify(long id, Food food) {
        Food toModify = findById(id);
        toModify.setName(food.getName());
        return toModify.getId();
    }

    @Transactional
    public boolean delete(long id) {
        Food del = findById(id);
        if (del == null) {
            return false;
        }
        delete(del);
        return true;
    }

    @Transactional
    public void reset() {
        for (Food food : getAll()) {
            delete(food.getId());
        }
    }
}
