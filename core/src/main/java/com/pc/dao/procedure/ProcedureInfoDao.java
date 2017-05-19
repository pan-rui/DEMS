package com.pc.dao.procedure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import com.pc.core.DataSource;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: wady (2017-03-27 14:21)
 * @version: \$Rev: 1693 $
 * @UpdateAuthor: \$Author: zhangj $
 * @UpdateDateTime: \$Date: 2017-05-02 10:38:50 +0800 (周二, 02 5月 2017) $
 */
@Repository
@CacheConfig(cacheNames = "qCache", cacheManager = "cacheManagerSlave", cacheResolver = "baseImpl")
public class ProcedureInfoDao {
        private Logger logger = LogManager.getLogger(this.getClass());

	private SqlSessionTemplate sqlSessionTemplate;
	private String className;

	@Autowired
	public ProcedureInfoDao(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
		this.className = this.getClass().getName();
	}

	public Connection getConnection() {
		return sqlSessionTemplate.getConnection();
	}

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}
	
	@DataSource
	public List<Map<String, Object>> queryProcedureTreeInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryProcedureTreeInTab", paramsMap);
	}
	
	@DataSource
	public List<Map<String, Object>> queryListByUserInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryListByUserInTab", paramsMap);
	}

	@DataSource
	public List<Map<String, Object>> queryListByRegionInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryListByRegionInTab", paramsMap);
	}
	
}
