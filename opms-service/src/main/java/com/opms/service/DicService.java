package com.opms.service;

import com.opms.base.BaseService;
import com.opms.entity.Dic;

public interface DicService extends BaseService<Dic> {
	public Dic isExist(Dic dic);
}
