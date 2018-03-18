package com.expertsoft.core.service;

import com.expertsoft.core.exception.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryService<T, ID, R extends JpaRepository<T, ID>> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> clazz;
    protected final R repository;

    public RepositoryService(final R repository, final Class<T> clazz) {
        this.repository = repository;
        this.clazz = clazz;
    }

    @Transactional(readOnly = true)
    public List<T> findAllById(Iterable<ID> ids) {
        List<T> result = new ArrayList<>();
        List<ID> notCachedIds = new ArrayList<>();
        Cache cache = entityManager.getEntityManagerFactory().getCache();

        for (ID id : ids) {
            if (cache.contains(clazz, id)) {
                result.add(repository.findById(id)
                        .orElseThrow(RecordNotFoundException::new));
            } else {
                notCachedIds.add(id);
            }
        }

        if (!notCachedIds.isEmpty()) {
            result.addAll(repository.findAllById(notCachedIds));
        }

        return result;
    }

    @Transactional(readOnly = true)
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(RecordNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

}
