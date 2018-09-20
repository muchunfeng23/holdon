package com.mcf.db.manager;

import java.util.LinkedHashMap;
import java.util.List;

public interface BaseManager<T, P, ID> {
	int insert(T arg0);

	int insertNotNull(T arg0);

	int insertBatch(List<T> arg0, Class arg1);

	int updateById(T arg0, ID arg1);

	int delete(T arg0);

	int deleteById(ID arg0);

	int deleteByIds(List<ID> arg0);

	T getById(ID arg0);

	List<T> getByIds(List<ID> arg0);

	List<T> select(P arg0, LinkedHashMap<String, String> arg1, Integer arg2, Integer arg3);

	List<T> select(P arg0, Integer arg1, Integer arg2);

	List<T> select(P arg0, LinkedHashMap<String, String> arg1);

	List<T> select(P arg0);

	int selectCount(P arg0);

	List<T> selectElse(T arg0, LinkedHashMap<String, String> arg1, Integer arg2, Integer arg3);

	List<T> selectElse(T arg0, Integer arg1, Integer arg2);

	List<T> selectElse(T arg0, LinkedHashMap<String, String> arg1);

	int selectCountElse(T arg0);
}
