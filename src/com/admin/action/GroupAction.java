package com.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.admin.constant.GroupType;
import com.admin.constant.SysParamCode;
import com.admin.model.SysParam;
import com.admin.model.WebCategory;
import com.admin.model.WebGroup;
import com.core.app.action.base.BaseAction;
import com.core.app.action.base.ResponseData;
import com.core.app.action.base.ResponseUtils;
import com.core.jdbc.util.PageBean;

/**
 * 网站资源分組展示类
 * 
 * @author tanson lam
 * 
 */
public class GroupAction extends BaseAction {

	private static Logger logger = Logger.getLogger(MainAction.class);
	private static final String LIST_PAGE = "/admin/group/grouplist.html";
	private static final String EDIT_FORM = "/admin/group/groupform.html";

	/**
	 * 查詢列表
	 */
	public void show() {
		try {
			Map<String, Object> dataModel = new HashMap<String, Object>();
			String pageStr = request.getParameter("page");
			String pageSizeStr = request.getParameter("pageSize");
			Integer page = StringUtils.isEmpty(pageStr) ? null : Integer
					.valueOf(pageStr);
			Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null
					: Integer.valueOf(pageSizeStr);
			String condition = bulidConditionSql();
			PageBean pageBean = baseModelService.pageQuery(condition,
					bulidOrderBySql(), pageSize, page, WebGroup.class);
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupType = sysParamService
					.getSysParam(SysParamCode.GROUP_TYPE);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupType", groupType);
			pageBean.setPageUrl(getPaginationUrl("/Admin-Group-show.action"));
			dataModel.put("pageBean", pageBean); 
			render(LIST_PAGE, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 显示编辑表单
	 */
	public void showEditForm() {
		Map<String, Object> dataModel = new HashMap<String, Object>();
		try {
			String id = request.getParameter("id");
			if (!StringUtils.isEmpty(id)) {
				WebGroup webGroup = findOne(Integer.valueOf(id), WebGroup.class);
				dataModel.put("bean", webGroup);
			}
			List<SysParam> iseffCode = sysParamService
					.getSysParam(SysParamCode.ISEFF);
			List<SysParam> groupType = sysParamService
					.getSysParam(SysParamCode.GROUP_TYPE);
			dataModel.put("iseffCode", iseffCode);
			dataModel.put("groupType", groupType);
			render(EDIT_FORM, dataModel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 保存导航栏
	 */
	public void save() {
		ResponseData responseData = null;
		try {
			String id = request.getParameter("id");
			WebGroup webGroup = new WebGroup();
			this.buildPageData(webGroup);

			if (!StringUtils.isEmpty(id)) {
				webGroup.setId(Integer.valueOf(id));
				baseModelService.update(webGroup);
			} else {
				baseModelService.save(webGroup);
			}
			responseData = ResponseUtils.success("保存成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("保存失败！");
		}
		this.outResult(responseData);
	}

	/**
	 * 删除导航栏
	 */
	public void delete() {
		ResponseData responseData = null;
		try {
			String selectItems = request.getParameter("selectItems");
			if (selectItems.isEmpty()) {
				responseData = ResponseUtils.fail("没有选中的数据！");
				this.outResult(responseData);
				return;
			}
			String[] selectArr = selectItems.split(",");
			for (String id : selectArr) {
				WebGroup webGroup = baseModelService.findOne(
						Integer.valueOf(id), WebGroup.class);
				if (webGroup != null)
					baseModelService.delete(webGroup);
			}
			responseData = ResponseUtils.success("删除成功！");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData = ResponseUtils.fail("删除失败！");
		}
		this.outResult(responseData);
	}
}
