package com.opms.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;

public class BeanUtil {

	/**
	 * 将对象集合按单属性排序
	 * 
	 * @param tList
	 *            需要排序的对象列表
	 * @param property
	 *            T对象中的某属性名，值不许为null
	 * @param reversed
	 *            ture为逆序，false为升序
	 */
	public static <T> void sort(List<T> tList, String property, boolean reversed) {
		BeanComparator<T> comparator = newBeanComparator(property, reversed);
		Collections.sort(tList, comparator);
	}

	/**
	 * 将对象集合按复合属性排序.propertys与reverseds一一对应
	 * 
	 * @param tList
	 *            需要排序的对象列表
	 * @param propertys
	 *            T对象中的属性名，可以多个
	 * @param reverseds
	 *            某属性倒序排列，ture为降序，false为升序
	 */
	@SuppressWarnings("unchecked")
	public static <T> void sort(List<T> tList, String[] propertys, boolean[] reverseds) {
		List<BeanComparator<T>> beanComparators = new ArrayList<BeanComparator<T>>();
		for (int i = 0; i < propertys.length; i++) {
			BeanComparator<T> comparator = newBeanComparator(propertys[i], reverseds[i]);
			beanComparators.add(comparator);
		}
		ComparatorChain multiSort = new ComparatorChain(beanComparators);
		Collections.sort(tList, multiSort);
	}

	/**
	 * 创建属性比较器
	 * 
	 * @param <T>
	 * 
	 * @param property
	 * @param reversed
	 *            属性倒序排列，ture为逆序，false为升序
	 * @return
	 */
	private static <T> BeanComparator<T> newBeanComparator(String property, boolean reversed) {
		Comparator<?> mycmp = ComparableComparator.getInstance();
		mycmp = ComparatorUtils.nullLowComparator(mycmp); // 允许null
		if (reversed) {
			mycmp = ComparatorUtils.reversedComparator(mycmp); // 逆序
		}
		return new BeanComparator<T>(property, mycmp);
	}

	/**
	 * 给Bean对象某属性设值
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static void setProperty(Object bean, String name, Object value) throws Exception {
		BeanUtils.setProperty(bean, name, value);
	}

	/**
	 * 填充多个属性值
	 * 
	 * @param bean
	 * @param map
	 *            属性值：key为属性名称，value为对应值
	 * @throws Exception
	 */
	public static void populateProperty(Object bean, Map<String, Object> map) throws Exception {
		BeanUtils.populate(bean, map);
	}

	/**
	 * 获取Bean对象的某属性值
	 * 
	 * @param bean
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static String getProperty(Object bean, String name) throws Exception {
		return BeanUtils.getSimpleProperty(bean, name);
	}
}
