package com.mcf.db.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.mcf.db.dao.BaseDao;
import com.mcf.db.manager.BaseManager;

public class BaseManagerImpl<T, P, ID> implements BaseManager<T, P, ID> {
	private static final Logger logger = LoggerFactory.getLogger(BaseManagerImpl.class);
	@Autowired(required = false)
	private BaseDao<T, P, ID> baseDao;
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public int insert(T object) {
		return this.baseDao.insert(object).intValue();
	}

	public int insertNotNull(T object) {
		return this.baseDao.insertNotNull(object).intValue();
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public int insertBatch(List<T> list, Class daoClass) {
		Long currentTime = Long.valueOf(System.currentTimeMillis());
		SqlSession sqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
		sqlSession.insert(daoClass.getName() + ".insertBatch", list);
		sqlSession.commit();
		sqlSession.close();
		logger.info("insertBatch sqlSession insert : " + (System.currentTimeMillis() - currentTime.longValue()));
		return 1;
	}

	public int updateById(T t, ID id) {
		return this.baseDao.updateById(t, id).intValue();
	}

	public int delete(T object) {
		return this.baseDao.delete(object).intValue();
	}

	public int deleteById(ID id) {
		return this.baseDao.deleteById(id).intValue();
	}

	public int deleteByIds(List<ID> list) {
		return this.baseDao.deleteByIds(list).intValue();
	}

	public List<T> select(P object, LinkedHashMap<String, String> sort, Integer offset, Integer limit) {
		return this.baseDao.select(object, sort, offset, limit);
	}

	public List<T> select(P object, LinkedHashMap<String, String> sort) {
		return this.baseDao.select(object, sort);
	}

	public List<T> select(P object, Integer offset, Integer limit) {
		return this.baseDao.select(object, (LinkedHashMap) null, offset, limit);
	}

	public List<T> select(P object) {
		return this.baseDao.select(object, (LinkedHashMap) null, (Integer) null, (Integer) null);
	}

	public int selectCount(P object) {
		return this.baseDao.selectCount(object).intValue();
	}

	public T getById(ID id) {
		return this.baseDao.getById(id);
	}

	public List<T> getByIds(List<ID> ids) {
		return this.baseDao.getByIds(ids);
	}

	public List<T> selectElse(T object, LinkedHashMap<String, String> sort, Integer offset, Integer limit) {
		return this.baseDao.selectElse(object, sort, offset, limit);
	}

	public List<T> selectElse(T object, Integer offset, Integer limit) {
		return this.baseDao.selectElse(object, (LinkedHashMap) null, offset, limit);
	}

	public List<T> selectElse(T object, LinkedHashMap<String, String> sort) {
		return this.baseDao.selectElse(object, sort, (Integer) null, (Integer) null);
	}

	public int selectCountElse(T object) {
		return this.baseDao.selectCountElse(object).intValue();
	}
}
