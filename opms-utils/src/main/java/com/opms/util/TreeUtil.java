package com.opms.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 把一个list集合,里面的bean含有 parentId 转为树形式
 * 
 */
public class TreeUtil {

	/**
	 * 根据父节点的ID获取所有子节点
	 * 
	 * @param list
	 *            分类表
	 * @param typeId
	 *            传入的父节点ID
	 * @return String
	 */
	public static List<TreeObject> getChildResources(List<TreeObject> list, int praentId) {
		List<TreeObject> returnList = new ArrayList<TreeObject>();
		for (Iterator<TreeObject> iterator = list.iterator(); iterator.hasNext();) {
			TreeObject t = iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == praentId) {
				recursionFn(list, t);
				returnList.add(t);
			}
		}
		return returnList;
	}

	/**
	 * 递归列表
	 * 
	 * @author LJN Email: mmm333zzz520@163.com
	 * @date 2013-12-4 下午7:27:30
	 * @param list
	 */
	private static void recursionFn(List<TreeObject> list, TreeObject t) {
		List<TreeObject> childList = getChildList(list, t);// 得到子节点列表
		t.setChildren(childList);
		for (TreeObject tChild : childList) {
			if (hasChild(list, tChild)) {// 判断是否有子节点
				Iterator<TreeObject> it = childList.iterator();
				while (it.hasNext()) {
					TreeObject n = it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	// 得到子节点列表
	private static List<TreeObject> getChildList(List<TreeObject> list, TreeObject t) {

		List<TreeObject> tlist = new ArrayList<TreeObject>();
		Iterator<TreeObject> it = list.iterator();
		while (it.hasNext()) {
			TreeObject n = it.next();
			if (n.getParentId() == t.getId()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	// 判断是否有子节点
	private static boolean hasChild(List<TreeObject> list, TreeObject t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}

}
