/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.iskyshop.foundation.service.impl;

import com.iskyshop.core.dao.IGenericDAO;
import com.iskyshop.core.query.GenericPageList;
import com.iskyshop.core.query.PageObject;
import com.iskyshop.core.query.support.IPageList;
import com.iskyshop.core.query.support.IQueryObject;
import com.iskyshop.foundation.domain.HomePage;
import com.iskyshop.foundation.service.IHomePageService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HomePageServiceImpl implements IHomePageService {

    @Resource(name = "homePageDAO")
    private IGenericDAO<HomePage> homePageDao;

    public boolean save(HomePage homePage) {
        try {
            this.homePageDao.save(homePage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public HomePage getObjById(Long id) {
        HomePage homePage = (HomePage) this.homePageDao.get(id);
        if (homePage != null) {
            return homePage;
        }
        return null;
    }

    public boolean delete(Long id) {
        try {
            this.homePageDao.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean batchDelete(List<Serializable> homePageIds) {
        for (Serializable id : homePageIds) {
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
        GenericPageList pList = new GenericPageList(HomePage.class, query, params, this.homePageDao);
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

    public boolean update(HomePage homePage) {
        try {
            this.homePageDao.update(homePage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<HomePage> query(String query, Map params, int begin, int max) {
        return this.homePageDao.query(query, params, begin, max);
    }
}