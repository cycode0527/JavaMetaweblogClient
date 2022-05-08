package top.sxrhhh.metaweblogClient.struct;

import java.util.Map;

/**
 * 分类
 * @author Sxrhhh
 * 2022/5/5 12:30
 * @version 1.0
 * @since 1.8
 */
public class CategoryInfo extends Struct {

    private String description;
    private String htmlUrl;
    private String rssUrl;
    private String title;
    private String categoryid;

    /**
     * 空构造方法
     */
    public CategoryInfo() {
    }

    /**
     * TODO
     * @author Sxrhhh
     * 2022/5/6 18:20
     * @param description TODO
     * @param htmlUrl TODO
     * @param rssUrl TODO
     * @param title TODO
     * @param categoryid TODO
     */
    public CategoryInfo(String description, String htmlUrl, String rssUrl, String title, String categoryid) {
        this.description = description;
        this.htmlUrl = htmlUrl;
        this.rssUrl = rssUrl;
        this.title = title;
        this.categoryid = categoryid;
    }

    /**
     * 直接通过结构体struct对象创建的构造方法.
     * @param struct 传入一个Map对象
     */
    public CategoryInfo(Map<String, Object> struct) {
        this.setStruct(struct);
    }

    @Override
    public void initMap() {
        super.initMap();

        // 将数据塞入map中
        map.put("description", description);
        map.put("htmlUrl", htmlUrl);
        map.put("rssUrl", rssUrl);
        map.put("title", title);
        map.put("categoryid", categoryid);
    }

    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public void setStruct(Map<String, Object> struct) {
        description = (String) struct.get("description");
        htmlUrl = (String) struct.get("htmlUrl");
        rssUrl = (String) struct.get("rssUrl");
        title = (String) struct.get("title");
        categoryid = (String) struct.get("categoryid");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getRssUrl() {
        return rssUrl;
    }

    public void setRssUrl(String rssUrl) {
        this.rssUrl = rssUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }
}
