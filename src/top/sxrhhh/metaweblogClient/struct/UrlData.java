package top.sxrhhh.metaweblogClient.struct;

import java.util.Map;

/**
 * Url信息类.
 * <p>存储URL信息,只有一个URL字符串对象,似乎并没有什么卵用
 * @author 19149
 * 2022/5/3 23:39
 * @version 1.0
 * @since 1.8
 */
public class UrlData extends Struct {

    private String url;

    /**
     * 空构造方法
     */
    public UrlData() {
    }


    /**
     * 带参构造方法
     * @author Sxrhhh
     * 2022/5/6 13:49
     * @param url URL字符串对象
     */
    public UrlData(String url) {
        this.url = url;
    }

    @Override
    public void initMap() {
        super.initMap();

        // 将数据塞入map中
        map.put("url", url);
    }



    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public void setStruct(Map<String, Object> struct) {
        url = (String) struct.get("url");
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
