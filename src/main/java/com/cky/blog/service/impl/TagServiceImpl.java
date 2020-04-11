package com.cky.blog.service.impl;

import com.cky.blog.NotFoundException;
import com.cky.blog.dao.TagMapper;
import com.cky.blog.entity.Tag;
import com.cky.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagMapper.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagMapper.getOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagMapper.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagMapper.findAll(pageable);
    }

    @Override
    public List<Tag> listTag() {
        return tagMapper.findAll();
    }

    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagMapper.findTop(pageable);
    }


    @Override
    public List<Tag> listTag(String ids) { //1,2,3
        return tagMapper.findAll();
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }


    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagMapper.getOne(id);
        if (t == null) {
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        return tagMapper.save(t);
    }



    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagMapper.deleteById(id);
    }
}
