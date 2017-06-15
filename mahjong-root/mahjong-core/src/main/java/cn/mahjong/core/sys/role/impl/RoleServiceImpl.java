package cn.mahjong.core.sys.role.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mahjong.core.base.impl.BaseServiceImpl;
import cn.mahjong.core.sys.role.RoleService;
import cn.mahjong.persist.sys.role.RoleDao;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	
}
