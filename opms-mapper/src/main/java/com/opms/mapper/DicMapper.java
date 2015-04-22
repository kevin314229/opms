package com.opms.mapper;

import com.opms.base.BaseMapper;
import com.opms.entity.Dic;

public interface DicMapper extends BaseMapper<Dic> {
	public Dic isExist(Dic dic);
}
