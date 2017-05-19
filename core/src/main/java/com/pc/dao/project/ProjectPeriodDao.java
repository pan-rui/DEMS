package com.pc.dao.project;

import com.pc.core.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * @Description: ${Description}
 * @Author: wady (2017-03-27 14:21)
 * @version: \$Rev: 1610 $
 * @UpdateAuthor: \$Author: zhangj $
 * @UpdateDateTime: \$Date: 2017-04-27 14:12:40 +0800 (周四, 27 4月 2017) $
 */
@Repository
@CacheConfig(cacheNames = "qCache", cacheManager = "cacheManagerSlave", cacheResolver = "baseImpl")
public class ProjectPeriodDao {
        private Logger logger = LogManager.getLogger(this.getClass());

	private SqlSessionTemplate sqlSessionTemplate;
	private String className;

	@Autowired
	public ProjectPeriodDao(SqlSessionTemplate sqlSessionTemplate) {
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
	public List<Map<String, Object>> queryProjectPeriodListByUserInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryProjectPeriodListByUserInTab", paramsMap);
	}

	@DataSource
	public List<Map<String, Object>> queryProjectPeriodDetailListInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryProjectPeriodDetailListInTab", paramsMap);
	}

	@DataSource
	public List<Map<String, Object>> queryProjectPeriodDetailPageInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryProjectPeriodDetailPageInTab", paramsMap);
	}


}
