package com.sales.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sales.model.CustomerSOS;

@Repository
public interface CustomerSOSDao extends CrudRepository<CustomerSOS, Long>{

}
