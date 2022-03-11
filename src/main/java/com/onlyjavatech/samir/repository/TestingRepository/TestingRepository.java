package com.onlyjavatech.samir.repository.TestingRepository;

import com.onlyjavatech.samir.model.TestingModel.Testing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestingRepository extends CrudRepository<Testing,String> {
}
