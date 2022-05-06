package top.sxrhhh.metaweblogClient.struct;


import java.util.HashMap;
import java.util.Map;

/**
 * 最基本的结构体类,负责存储适合xmlrpc的对象,并以Map形式输出
 * @author Sxrhhh
 * 2022/5/3 22:56
 * @version 1.0
 * @since 1.8
 */
public abstract class Struct implements HasMap {


    // 内部Map存储结构体
    protected Map<String, Object> map = new HashMap<>();

    /**
     *
     * @author Sxrhhh
     * 2022/5/3 23:20
     * 初始化Map列表
     */
    public void initMap() {
        this.map.clear();
    }



    /**
     * @return Map对象  属于结构体
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public Map<String, Object> getStruct() {
        initMap();  // 初始化Map

        return new HashMap<>(this.map);
    }

    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public abstract void setStruct(Map<String, Object> struct);






}
