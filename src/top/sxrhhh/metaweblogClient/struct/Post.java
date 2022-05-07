package top.sxrhhh.metaweblogClient.struct;

import java.util.Date;
import java.util.Map;

/**
 * Post博客文章类
 * @author Sxrhhh
 * 2022/5/5 12:36
 * @version 1.0
 * @since 1.8
 */
public class Post extends Struct {

    private Date dateCreated;
    private String description;
    private String title;
    private String[] categories;

    public Post() {
    }

    /**
     *
     * @author Sxrhhh
     * 2022/5/6 18:18
     * @param dateCreated 创建时间(可以直接new Date)
     * @param description 文章正文
     * @param title 文章标题
     */
    public Post(Date dateCreated, String description, String title) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.title = title;
    }

    /**
     *
     * @author Sxrhhh
     * 2022/5/6 13:50
     * @param dateCreated 创建时间(可以直接new Date)
     * @param description 文章正文
     * @param title 文章标题
     * @param categories (可选)文章加入分类
     */
    public Post(Date dateCreated, String description, String title, String[] categories) {
        this.dateCreated = dateCreated;
        this.description = description;
        this.title = title;
        this.categories = categories;
    }

    @Override
    public void initMap() {
        super.initMap();

        // 将数据塞入map中
        map.put("dateCreated", dateCreated);
        map.put("description", description);
        map.put("title", title);

        // 可选数据
        if (categories != null) {
            map.put("slug", categories);
        }
    }

    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public void setStruct(Map<String, Object> struct) {
        dateCreated = (Date) struct.get("dateCreated");
        description = (String) struct.get("description");
        title = (String) struct.get("title");
        // 生成categories
        Object[] ob =  (Object[]) struct.get("categories");
        categories = new String[ob.length];
        for (int i = 0; i < ob.length; i++) {
            categories[i] = (String) ob[i];
        }
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getCategories() {
        return categories;
    }

    public void setCategories(String[] categories) {
        this.categories = categories;
    }
}
