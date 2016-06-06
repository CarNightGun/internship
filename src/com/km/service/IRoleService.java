package com.km.service;


import com.km.bean.Role;
import com.km.common.service.IBaseService;
import com.km.dao.IRoleDao;
import com.km.util.page.IPageList;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:05:57
 */
public interface IRoleService extends IBaseService<Long, Role, IRoleDao>
{
	public IPageList<Role> listPage(Role search, int pageNo, int pageSize);

	public void saveAuthorize(Long roleId, Long[] authorityIds);

	public void changeAuditState(String pkuids);

	public void delete(String roleids);
}
