package at.htl.zoomanagement.repository;

import at.htl.zoomanagement.entity.Enclosure;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class EnclosureRepository implements PanacheRepository<Enclosure> {

    public List<Enclosure> getAll() {
        return listAll();
    }

    public Enclosure findById(long id) {
        return find("id", id).firstResult();
    }

    @Transactional
    public long add(Enclosure enclosure) {
        persist(enclosure);
        return enclosure.getId();
    }

    @Transactional
    public long modify(long id, Enclosure enclosure) {
        Enclosure toModify = findById(id);
        toModify.setName(enclosure.getName());
        return toModify.getId();
    }

    @Transactional
    public boolean delete(long id) {
        Enclosure del = findById(id);
        if (del == null) {
            return false;
        }
        delete(del);
        return true;
    }

    @Transactional
    public void reset() {
        for (Enclosure enclosure : getAll()) {
            delete(enclosure.getId());
        }
    }
}
