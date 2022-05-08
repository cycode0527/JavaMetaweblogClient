package top.sxrhhh.metaweblogClient.struct;

import java.util.Map;

/**
 * 博客信息
 * @author Sxrhhh
 * 2022/5/3 23:16
 * @version 1.0
 * @since 1.8
 */
public class BlogInfo extends Struct{
    private String blogid;
    private String url;
    private String blogName;

    public BlogInfo() {
    }

    /**
     * TODO
     * @author Sxrhhh
     * 2022/5/6 18:22
     * @param blogid 博客ID
     * @param url 博客地址
     * @param blogName 博客名字
     */
    public BlogInfo(String blogid, String url, String blogName) {
        this.blogid = blogid;
        this.url = url;
        this.blogName = blogName;
    }

    /**
     * 直接通过结构体struct对象创建的构造方法.
     * @param struct 传入一个Map对象
     */
    public BlogInfo(Map<String, Object> struct) {
        this.setStruct(struct);
    }

    @Override
    public void initMap() {
        super.initMap();

        // 将数据塞入map中
        map.put("blogid", blogid);
        map.put("url", url);
        map.put("blogName", blogName);
    }

    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     * 通过Map结构体设置本对象
     */
    @Override
    public void setStruct(Map<String, Object> struct) {
        blogid = (String) struct.get("blogId");
        url = (String) struct.get("url");
        blogName = (String) struct.get("blogName");
    }


    public String getBlogid() {
        return blogid;
    }

    public void setBlogid(String blogid) {
        this.blogid = blogid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }


}
