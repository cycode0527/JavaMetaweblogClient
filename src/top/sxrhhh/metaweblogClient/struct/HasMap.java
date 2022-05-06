package top.sxrhhh.metaweblogClient.struct;

import java.util.Map;

/**
 * 拥有Map类的接口
 * @author Sxrhhh
 * 2022/5/3 22:54
 * @version 1.0
 * @since 1.8
 */
public interface HasMap {

    public Map<String, Object> getStruct();

    public void setStruct(Map<String, Object> struct);

}
