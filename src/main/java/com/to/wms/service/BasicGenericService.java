package com.to.wms.service;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BasicGenericService<RepositoryT extends MongoRepository<?, String>> {

    private final RepositoryT repositoryT;

    public BasicGenericService(RepositoryT repositoryT) {
        this.repositoryT = repositoryT;
    }

    @Transactional(readOnly = true)
    public List<?> getAll() {
        return repositoryT.findAll();
    }

    public void deleteById(String id) {
        repositoryT.deleteById(id);
    }
}
