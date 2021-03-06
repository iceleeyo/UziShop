/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.iskyshop.foundation.service.impl;

import com.iskyshop.core.dao.IGenericDAO;
import com.iskyshop.core.query.GenericPageList;
import com.iskyshop.core.query.PageObject;
import com.iskyshop.core.query.support.IPageList;
import com.iskyshop.core.query.support.IQueryObject;
import com.iskyshop.foundation.domain.Predeposit;
import com.iskyshop.foundation.service.IPredepositService;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PredepositServiceImpl implements IPredepositService {

    @Resource(name = "predepositDAO")
    private IGenericDAO<Predeposit> predepositDao;

    public boolean save(Predeposit predeposit) {
        try {
            this.predepositDao.save(predeposit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Predeposit getObjById(Long id) {
        Predeposit predeposit = (Predeposit) this.predepositDao.get(id);
        if (predeposit != null) {
            return predeposit;
        }
        return null;
    }

    public boolean delete(Long id) {
        try {
            this.predepositDao.remove(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean batchDelete(List<Serializable> predepositIds) {
        for (Serializable id : predepositIds) {
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
        GenericPageList pList = new GenericPageList(Predeposit.class, query, params, this.predepositDao);
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

    public boolean update(Predeposit predeposit) {
        try {
            this.predepositDao.update(predeposit);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Predeposit> query(String query, Map params, int begin, int max) {
        return this.predepositDao.query(query, params, begin, max);
    }
}