package com.moon.senla.dao;

import com.moon.senla.api.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDao<T> implements GenericDao<T> {
  private static final Logger logger = LoggerFactory.getLogger(AbstractDao.class);
}
