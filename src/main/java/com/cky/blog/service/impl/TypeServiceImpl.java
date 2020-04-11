package com.cky.blog.service.impl;

import com.cky.blog.NotFoundException;
import com.cky.blog.dao.TypeMapper;
import com.cky.blog.entity.Type;
import com.cky.blog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeMapper.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeMapper.getOne(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeMapper.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeMapper.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeMapper.findAll();
    }


    @Override
    public List<Type> listTypeTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return typeMapper.findTop(pageable);
    }


    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeMapper.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeMapper.save(t);
    }



    @Transactional
    @Override
    public void deleteType(Long id) {
        typeMapper.deleteById(id);
    }
}
