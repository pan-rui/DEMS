package com.pc.controller.auth;

import com.pc.annotation.EncryptProcess;
import com.pc.base.BaseResult;
import com.pc.base.Constants;
import com.pc.base.ReturnCode;
import com.pc.controller.BaseController;
import com.pc.core.Page;
import com.pc.core.TableConstants;
import com.pc.service.auth.DataSceneRuleService;
import com.pc.util.DateUtil;
import com.pc.vo.ParamsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: 数据场景规则
 * @Author: wady (2017-03-28 20:37)
 * @version: 1.0
 * @UpdateAuthor: wady
 * @UpdateDateTime: 2017-03-28
 */
@Controller
@RequestMapping("/admin")
public class DataSceneRuleController extends BaseController {
	@Autowired
	private DataSceneRuleService dataSceneRuleService;

	@RequestMapping("/dataSceneRule/add")
	@ResponseBody
	public BaseResult add(@RequestAttribute(Constants.USER_ID) String userId,
			@RequestHeader(Constants.TENANT_ID) String tenantId, @EncryptProcess ParamsVo params,
			@RequestAttribute String ddBB) {
		Map<String, Object> map = new LinkedHashMap<>(params.getParams());
		map.put(TableConstants.TENANT_ID, tenantId);
		map.put(TableConstants.UPDATE_TIME, DateUtil.convertDateTimeToString(new Date(), null));
		map.put(TableConstants.UPDATE_USER_ID, userId);
		map.put(TableConstants.IS_VALID, 0);
		map.put(TableConstants.IS_SEALED, 0);
		dataSceneRuleService.addDataSceneRule(map, ddBB);
		return new BaseResult(ReturnCode.OK);
	}

	@RequestMapping("/dataSceneRule/delete")
	@ResponseBody
	public BaseResult delete(@RequestAttribute(Constants.USER_ID) String userId, @EncryptProcess ParamsVo params,
			@RequestAttribute String ddBB) {
		Map<String, Object> map = new LinkedHashMap<>(params.getParams());
		map.put(TableConstants.SEALED_TIME, DateUtil.convertDateTimeToString(new Date(), null));
		map.put(TableConstants.SEALED_USER_ID, userId);
		boolean b = dataSceneRuleService.deleteDataSceneRule(map, ddBB);
		if (b) {
			return new BaseResult(ReturnCode.OK);
		} else {
			return new BaseResult(ReturnCode.FAIL);
		}
	}

	@RequestMapping("/dataSceneRule/edit")
	@ResponseBody
	public BaseResult edit(@EncryptProcess ParamsVo params, @RequestAttribute(Constants.USER_ID) String userId,
			@RequestAttribute String ddBB) {
		Map<String, Object> map = new LinkedHashMap<>(params.getParams());
		map.put(TableConstants.UPDATE_TIME, DateUtil.convertDateTimeToString(new Date(), null));
		map.put(TableConstants.UPDATE_USER_ID, userId);
		boolean b = dataSceneRuleService.updateDataSceneRule(map, ddBB);
		if (b) {
			return new BaseResult(ReturnCode.OK);
		} else {
			return new BaseResult(ReturnCode.FAIL);
		}

	}

	@RequestMapping("/dataSceneRule/get")
	@ResponseBody
	public BaseResult get(@EncryptProcess ParamsVo params, @RequestAttribute String ddBB) {
		Map<String, Object> map = new LinkedHashMap<>(params.getParams());
		return new BaseResult(ReturnCode.OK, dataSceneRuleService.getDataSceneRule(map, ddBB));
	}

	@RequestMapping("/dataSceneRule/getList")
	@ResponseBody
	public BaseResult getList(@RequestHeader(Constants.TENANT_ID) String tenantId, @EncryptProcess ParamsVo params,
			@RequestAttribute String ddBB) {
		Map<String, Object> map = new LinkedHashMap<>(params.getParams());
		map.put(TableConstants.TENANT_ID, tenantId);
		map.put(TableConstants.IS_VALID, 0);
		map.put(TableConstants.IS_SEALED, 0);
		return new BaseResult(ReturnCode.OK, dataSceneRuleService.getDataSceneRuleList(map, ddBB));
	}

	@RequestMapping("/dataSceneRule/getPage")
	@ResponseBody
	public BaseResult getPage(@RequestHeader(Constants.TENANT_ID) String tenantId, @EncryptProcess Page page,
			@RequestAttribute String ddBB) {
		Map<String, Object> map = new LinkedHashMap<>(page.getParams());
		map.put(TableConstants.TENANT_ID, tenantId);
		map.put(TableConstants.IS_VALID, 0);
		map.put(TableConstants.IS_SEALED, 0);
		page.setParams(map);
		return new BaseResult(ReturnCode.OK, dataSceneRuleService.getDataSceneRulePage(page, ddBB));
	}
}
