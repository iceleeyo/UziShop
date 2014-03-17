/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.iskyshop.foundation.service.impl;

import com.iskyshop.core.dao.IGenericDAO;
import com.iskyshop.core.query.GenericPageList;
import com.iskyshop.core.query.PageObject;
import com.iskyshop.core.query.support.IPageList;
import com.iskyshop.core.query.support.IQueryObject;
import com.iskyshop.foundation.domain.Group;
import com.iskyshop.foundation.service.IGroupService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupServiceImpl implements IGroupService {

    @Resource(name = "groupDAO")
    private IGenericDAO<Group> groupDao;

    public boolean save(Group group) {
        try {
            this.groupDao.save(group);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Group getObjById(Long id) {
        Group group = (Group) this.groupDao.get(id);
        if (group != null) {
            return group;
        }
        return null;
    }

    public boolean delete(Long id) {
        try {
            this.groupDao.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean batchDelete(List<Serializable> groupIds) {
        for (Serializable id : groupIds) {
            delete((Long) id);
        }
        return true;
    }

    public IPageList list(IQueryObject properties) {
        if (properties == null) {
            return null;
        }
        String query = properties.getQuery();
        Map params = properties.getParameters();
        GenericPageList pList = new GenericPageList(Group.class, query, params, this.groupDao);
        if (properties != null) {
            PageObject pageObj = properties.getPageObj();
            if (pageObj != null)
                pList.doList((pageObj.getCurrentPage() == null) ? 0 : pageObj.getCurrentPage().intValue(),
                        (pageObj.getPageSize() == null) ? 0 : pageObj.getPageSize().intValue());
        } else {
            pList.doList(0, -1);
        }
        return pList;
    }

    public boolean update(Group group) {
        try {
            this.groupDao.update(group);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Group> query(String query, Map params, int begin, int max) {
        return this.groupDao.query(query, params, begin, max);
    }
}