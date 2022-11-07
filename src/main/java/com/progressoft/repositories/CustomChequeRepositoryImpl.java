package com.progressoft.repositories;

import com.progressoft.entities.Cheque;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomChequeRepositoryImpl implements CustomChequeRepository{

    @PersistenceContext
    EntityManager entityManager;
    CriteriaBuilder criteriaBuilder;
    CriteriaQuery<Cheque> criteriaQuery;
    Root<Cheque> chequeRoot;
    List<Predicate> predicates;

    public CustomChequeRepositoryImpl() {
        super();
    }

    @Override
    public List<Cheque> findChequesByAllFields(
            Long id,
            BigDecimal amount,
            String number,
            String digit
    ) {
        criteriaBuilder = entityManager.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(Cheque.class);
        chequeRoot = criteriaQuery.from(Cheque.class);
        predicates = new ArrayList<>();

        updatePredicate("id", id);
        updatePredicate("amount", amount);
        updatePredicate("number", number);
        updatePredicate("digit", digit);

        if (!predicates.isEmpty()) {
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            criteriaQuery.where(finalPredicate);
        }

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private void updatePredicate(String fieldName, Object value) {
        if (value != null) {
            Predicate predicate = criteriaBuilder.equal(chequeRoot.get(fieldName), value);
            predicates.add(predicate);
        }
    }
}
