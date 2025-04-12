package br.com.audsat.vehicleinsurancews.useful;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class GenericRepository {
    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    public void executeQuery(String query) {
        Query nativeQuery = entityManager.createNativeQuery(query);
        nativeQuery.executeUpdate();
    }

}