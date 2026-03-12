package com.springboot.journal_app.respository;

import com.springboot.journal_app.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserRepoImpl {

    @PersistenceContext //same as autowire but it is sync with the current transaction where as autowired is spring specific
    private EntityManager entityManager;

    public List<User> getUserForSA(){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        /*
        criteriaQuery.select(root)
                .where(
                        criteriaBuilder.and(
                                criteriaBuilder.isNotNull(root.get("email")),
                                criteriaBuilder.equal(root.get("sentimentAnalysis"), true)
                        )
                );

         */
        Predicate emailExist = criteriaBuilder.and(
                criteriaBuilder.isNotNull(root.get("email")),
                criteriaBuilder.notEqual(root.get("email"), "")
        );
        Predicate sentimentExist = criteriaBuilder.equal(root.get("sentimentAnalysis"), true);
        criteriaQuery.select(root).
                where(
                        criteriaBuilder.and(
                                emailExist,
                                sentimentExist
                        )
                );
        List<User> resultList = entityManager.createQuery(criteriaQuery).getResultList();
        return resultList;

    }
}
