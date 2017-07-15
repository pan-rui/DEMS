package com.pc.dao.acceptance;

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
 * @version: \$Rev: 3514 $
 * @UpdateAuthor: \$Author: zhangj $
 * @UpdateDateTime: \$Date: 2017-07-11 09:13:04 +0800 (周二, 11 7月 2017) $
 */
@Repository
@CacheConfig(cacheNames = "qCache", cacheManager = "cacheManagerSlave", cacheResolver = "baseImpl")
public class AcceptanceNoteDao {
        private Logger logger = LogManager.getLogger(this.getClass());

	private SqlSessionTemplate sqlSessionTemplate;
	private String className;

	@Autowired
	public AcceptanceNoteDao(SqlSessionTemplate sqlSessionTemplate) {
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
	public List<Map<String, Object>> queryAcceptanceNoteByMonthInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryAcceptanceNoteByMonthInTab", paramsMap);
	}
	
	@DataSource
	public List<Map<String, Object>> queryUserAcceptanceCountByMonthInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryUserAcceptanceCountByMonthInTab", paramsMap);
	}
	
	@DataSource
	public List<Map<String, Object>> queryUserAcceptanceStatisticsByPostInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryUserAcceptanceStatisticsByPostInTab", paramsMap);
	}
	
	@DataSource
	public List<Map<String, Object>> queryProjectAcceptanceStatisticsRankingInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryProjectAcceptanceStatisticsRankingInTab", paramsMap);
	}
	
	@DataSource
	public List<Map<String, Object>> queryTeamAcceptanceStatisticsRankingInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryTeamAcceptanceStatisticsRankingInTab", paramsMap);
	}
	
	@DataSource
	public List<Map<String, Object>> queryCompanyAcceptanceStatisticsRankingInTab(Map<String, Object> paramsMap) {
		return sqlSessionTemplate.selectList(className + ".queryCompanyAcceptanceStatisticsRankingInTab", paramsMap);
	}
	
}
