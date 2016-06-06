package com.km.service;

import java.util.List;

import com.km.bean.User;
import com.km.common.service.IBaseService;
import com.km.dao.IUserDao;
import com.km.util.page.IPageList;



/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月12日下午2:23:31
 */
public interface IUserService extends IBaseService<Long, User , IUserDao>
{

	public User login(String username, String password);

	public IPageList<User> listPage(User entity, int pageNo, int pageSize);
	
	public void changeAuditState(String pkuids);
	
	public void delete(String userids);

	public void updateRoleOrg(Long id, Long roleId, Long organizationId);	
	
	public List<User> listAllAndInitOrgAndRole();
}
