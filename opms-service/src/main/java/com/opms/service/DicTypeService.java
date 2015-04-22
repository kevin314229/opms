package com.opms.service;

import com.opms.base.BaseService;
import com.opms.entity.DicType;

public interface DicTypeService extends BaseService<DicType> {
	public DicType isExist(DicType dicType);

	public DicType queryById(DicType dicType);
}
