package com.km.service;

import com.km.bean.Authority;
import com.km.common.service.ITreeService;
import com.km.dao.IAuthorityDao;

/**
 * @author tcn 空幕  email:1623092203@qq.com time:2016年5月20日上午8:32:41
 */
public interface IAuthorityService extends ITreeService<Long, Authority, IAuthorityDao>
{

}
