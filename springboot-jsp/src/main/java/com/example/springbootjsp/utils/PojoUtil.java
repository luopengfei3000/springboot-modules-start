//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.springbootjsp.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

/**
 * ****科技有限责任公司
 * 定义 Pojo工具类
 * @author luopf
 * @data 2019/1/16
 */
public class PojoUtil {
    static Logger logger = LoggerFactory.getLogger(PojoUtil.class);
    private static final long VERSION = 0L;

    public PojoUtil() {
    }

    /**
     * 将字符串aa_bb_cc或者aa-bb-cc或AaBbCc(第一个大写转化小写)转化为aaBbCc驼峰式
     * @param s
     * @return java.lang.String
     * @Author luopf 2019/1/16
     */
    public static String getJavaNameFromDBColumnName(String s) {
        String string = toUpperCamelCase(s);
        return Introspector.decapitalize(string);
    }

    /**
     * 将dto对象转化为map集合 例如：Person p = new Person('1','luo'); 转化结果：{id=1, name=luo, class=class mms.pms.pmsinvoice.controller.Person}
     * @param bean
     * @return java.util.Map<?,?>
     * @Author luopf 2019/1/16
     */
    public static Map<?, ?> toMap(Object bean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Assert.notNull(bean, "参数 bean 不能为空");
        return PropertyUtils.describe(bean);
    }

    /**
     * 将map转化为dto
     * @param map
     * @param target
     * @return java.lang.Object
     * @Author luopf 2019/1/16
     *
     * 用法：
     * Map<String,Object> map = new HashMap<String,Object>();
     * map.put("id","2");
     * map.put("name","pengfei");
     *Person p = new Person();
     * p = (Person)PojoUtil.Map2Pojo(map, p);
     */

    public static Object Map2Pojo(Map<String, Object> map, Object target) throws Exception {
        try {
            Iterator i$ = map.entrySet().iterator();

            while(true) {
                while(true) {
                    Entry s;
                    do {
                        if (!i$.hasNext()) {
                            return target;
                        }

                        s = (Entry)i$.next();
                    } while(s.getValue() == null);

                    String objName = getJavaNameFromDBColumnName((String)s.getKey());
                    Object objValue = s.getValue();
                    PropertyDescriptor propDes = BeanUtils.getPropertyDescriptor(target.getClass(), objName);
                    if (propDes != null && propDes.getPropertyType() == BigDecimal.class) {
                        if (objValue != null) {
                            BigDecimal big = new BigDecimal(objValue.toString());
                            PropertyUtils.setProperty(target, objName, big);
                        }
                    } else {
                        PropertyUtils.setProperty(target, objName, objValue);
                    }
                }
            }
        } catch (Exception var8) {
            logger.error(var8.getMessage());
            throw new Exception(var8.getMessage(), var8);
        }
    }

    /**
     * 将fromObj的对象属性copy到toObj的对象中(将fromObj对象中所有属性copy，包括为空的属性)
     * @param toObj
     * @param fromObj
     * @return void
     * @Author luopf 2019/1/16
     */
    public static void copyProperties(Object toObj, Object fromObj) throws Exception {
        Assert.notNull(toObj, "参数 toObj 不能为空");
        Assert.notNull(fromObj, "参数 fromObj 不能为空");

        try {
            PropertyUtils.copyProperties(toObj, fromObj);
        } catch (Exception var3) {
            throw new Exception(var3.getMessage(), var3);
        }
    }

    /**
     * 将fromObj的对象属性copy到toObj的对象中(如果skipNull为true,则将fromObj对象中所有不为空的属性copy到toObj对象中【fromObj对象为空的属性不copy，继续保留toObj对象中原有的的属性】)
     * @param toObj
     * @param fromObj
     * @param skipNull 一般为true
     * @return void
     * @Author luopf 2019/1/16
     */
    public static void copyProperties(Object toObj, Object fromObj, boolean skipNull) throws Exception {
        Assert.notNull(toObj, "参数 toObj 不能为空");
        Assert.notNull(fromObj, "参数 fromObj 不能为空");
        Object dest = toObj;
        Object orig = fromObj;
        PropertyUtilsBean utilsBean = BeanUtilsBean.getInstance().getPropertyUtils();
        if (toObj == null) {
            throw new IllegalArgumentException("No destination bean specified");
        } else if (fromObj == null) {
            throw new IllegalArgumentException("No origin bean specified");
        } else {
            int i;
            String name;
            Object value;
            if (!(fromObj instanceof DynaBean)) {
                if (fromObj instanceof Map) {
                    Iterator entries = ((Map)fromObj).entrySet().iterator();

                    while(true) {
                        Entry entry;
                        do {
                            if (!entries.hasNext()) {
                                return;
                            }

                            entry = (Entry)entries.next();
                            name = (String)entry.getKey();
                        } while(!utilsBean.isWriteable(dest, name));

                        try {
                            value = entry.getValue();
                            if (!skipNull || null != value) {
                                if (dest instanceof DynaBean) {
                                    ((DynaBean)dest).set(name, entry.getValue());
                                } else {
                                    utilsBean.setSimpleProperty(dest, name, entry.getValue());
                                }
                            }
                        } catch (NoSuchMethodException var13) {
                            logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var13);
                            throw new Exception(var13.getMessage(), var13);
                        } catch (IllegalAccessException var14) {
                            logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var14);
                            throw new Exception(var14.getMessage(), var14);
                        } catch (InvocationTargetException var15) {
                            logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var15);
                            throw new Exception(var15.getMessage(), var15);
                        }
                    }
                } else {
                    PropertyDescriptor[] origDescriptors = utilsBean.getPropertyDescriptors(fromObj);

                    for(i = 0; i < origDescriptors.length; ++i) {
                        name = origDescriptors[i].getName();
                        if (utilsBean.isReadable(orig, name) && utilsBean.isWriteable(dest, name)) {
                            try {
                                value = utilsBean.getSimpleProperty(orig, name);
                                if (!skipNull || null != value) {
                                    if (dest instanceof DynaBean) {
                                        ((DynaBean)dest).set(name, value);
                                    } else {
                                        utilsBean.setSimpleProperty(dest, name, value);
                                    }
                                }
                            } catch (NoSuchMethodException var10) {
                                logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var10);
                                throw new Exception(var10.getMessage(), var10);
                            } catch (IllegalAccessException var11) {
                                logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var11);
                                throw new Exception(var11.getMessage(), var11);
                            } catch (InvocationTargetException var12) {
                                logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var12);
                                throw new Exception(var12.getMessage(), var12);
                            }
                        }
                    }
                }
            } else {
                DynaProperty[] origDescriptors = ((DynaBean)fromObj).getDynaClass().getDynaProperties();

                for(i = 0; i < origDescriptors.length; ++i) {
                    name = origDescriptors[i].getName();
                    if (utilsBean.isReadable(orig, name) && utilsBean.isWriteable(dest, name)) {
                        try {
                            value = ((DynaBean)orig).get(name);
                            if (!skipNull || null != value) {
                                if (dest instanceof DynaBean) {
                                    ((DynaBean)dest).set(name, value);
                                } else {
                                    utilsBean.setSimpleProperty(dest, name, value);
                                }
                            }
                        } catch (NoSuchMethodException var16) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var16);
                                throw new Exception(var16.getMessage(), var16);
                            }
                        } catch (IllegalAccessException var17) {
                            logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var17);
                            throw new Exception(var17.getMessage(), var17);
                        } catch (InvocationTargetException var18) {
                            logger.debug("Error writing to '" + name + "' on class '" + dest.getClass() + "'", var18);
                            throw new Exception(var18.getMessage(), var18);
                        }
                    }
                }
            }

        }
    }

    /**
     * 将字符串aa_bb_cc或者aa-bb-cc转化为AaBbCc驼峰式
     * @param s
     * @return java.lang.String
     * @Author luopf 2019/1/16
     */
    public static String toUpperCamelCase(String s) {
        if ("".equals(s)) {
            return s;
        } else {
            StringBuffer result = new StringBuffer();
            boolean capitalize = true;
            boolean lastCapital = false;
            boolean lastDecapitalized = false;
            String p = null;

            for(int i = 0; i < s.length(); ++i) {
                String c = s.substring(i, i + 1);
                if (!"_".equals(c) && !" ".equals(c) && !"-".equals(c)) {
                    if (c.toUpperCase().equals(c)) {
                        if (lastDecapitalized && !lastCapital) {
                            capitalize = true;
                        }

                        lastCapital = true;
                    } else {
                        lastCapital = false;
                    }

                    if (capitalize) {
                        if (p != null && p.equals("_")) {
                            result.append(c.toLowerCase());
                            capitalize = false;
                            p = c;
                        } else {
                            result.append(c.toUpperCase());
                            capitalize = false;
                            p = c;
                        }
                    } else {
                        result.append(c.toLowerCase());
                        lastDecapitalized = true;
                        p = c;
                    }
                } else {
                    capitalize = true;
                }
            }

            String r = result.toString();
            return r;
        }
    }

}
