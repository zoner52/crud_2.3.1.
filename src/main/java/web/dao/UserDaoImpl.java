package web.dao;
import web.model.User;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user, long id) {
        User updatedUser = getUserById(id);
        updatedUser.setName(user.getName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setCar(user.getCar());
        return em.merge(updatedUser);
    }

    @Override
    public void deleteUser(long id) {
        em.remove(getUserById(id));
    }

    @Override
    public User getUserById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public List<User> getUsers() {
        return em.createQuery("select u FROM User u", User.class).getResultList();
    }
}