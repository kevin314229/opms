package com.opms.mapper;

import com.opms.base.BaseMapper;
import com.opms.entity.DicType;

public interface DicTypeMapper extends BaseMapper<DicType> {
	public DicType isExist(DicType dicType);

	public DicType queryById(DicType dicType);
}
