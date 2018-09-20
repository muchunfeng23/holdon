package com.mcf.db.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseDao<T, P, ID> {
	T getById(ID arg0);

	List<T> getByIds(List<ID> arg0);

	Integer selectCount(@Param("tb") P arg0);

	List<T> select(@Param("tb") P arg0, @Param("sort") LinkedHashMap<String, String> arg1,
			@Param("offset") Integer arg2, @Param("limit") Integer arg3);

	List<T> select(@Param("tb") P arg0, @Param("sort") LinkedHashMap<String, String> arg1);

	Integer insert(T arg0);

	Integer insertNotNull(T arg0);

	Integer insertBatch(List<T> arg0);

	Integer updateById(@Param("tb") T arg0, @Param("id") ID arg1);

	Integer delete(T arg0);

	Integer deleteById(ID arg0);

	Integer deleteByIds(List<ID> arg0);

	List<T> selectElse(@Param("tb") T arg0, @Param("sort") LinkedHashMap<String, String> arg1,
			@Param("offset") Integer arg2, @Param("limit") Integer arg3);

	Integer selectCountElse(@Param("tb") T arg0);
}
