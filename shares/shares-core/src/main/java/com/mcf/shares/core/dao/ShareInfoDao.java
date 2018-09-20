package com.mcf.shares.core.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.mcf.db.dao.BaseDao;
import com.mcf.shares.core.entity.ShareInfoEntity;

@Mapper
@Component
public interface ShareInfoDao extends BaseDao<ShareInfoEntity,ShareInfoEntity,Long>{
	
}
