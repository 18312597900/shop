package com.xunfang.service.impl;

import com.xunfang.dao.TypeDao;
import com.xunfang.pojo.Type;
import com.xunfang.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    @Override
    public List<Type> getAll() {
        return typeDao.selectAll();
    }

    @Override
    public int addType(Type type) {
        return typeDao.add(type);
    }

    @Override
    public int updateType(Type type) {
        return typeDao.update(type);
    }
}
