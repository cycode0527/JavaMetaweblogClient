package top.sxrhhh.metaweblogClient.struct;

import java.util.Map;

/**
 * 博客分类
 * @author Sxrhhh
 * 2022/5/4 0:01
 * @version 1.0
 * @since 1.8
 */
public class WpCategory extends Struct {
    private String name;
    private String slug;
    private Integer parent_id;
    private String description;

    /**
     * 空构造方法.
     */
    public WpCategory() {
    }

    /**
     * 最精简构造方法.
     * @author Sxrhhh
     * 2022/5/6 18:23
     * @param name 分类名称
     * @param parent_id 类id(填写0为无父类)
     */
    public WpCategory(String name, Integer parent_id) {
        this.name = name;
        this.parent_id = parent_id;
    }

    /**
     * 可初始化别名和描述的构造方法.
     * @author Sxrhhh
     * 2022/5/6 13:47
     * @param name 分类名称
     * @param slug (可选)分类别名(通常为英文小写加连接号)
     * @param parent_id 父类id(填写0为无父类)
     * @param description (可选)分类描述
     */
    public WpCategory(String name, String slug, Integer parent_id, String description) {
        this.name = name;
        this.slug = slug;
        this.parent_id = parent_id;
        this.description = description;
    }

    /**
     * 直接通过结构体struct对象创建的构造方法.
     * @param struct 传入一个Map对象
     */
    public WpCategory(Map<String, Object> struct) {
        this.setStruct(struct);
    }

    @Override
    public void initMap() {
        super.initMap();

        // 将数据塞入map中
        map.put("name", name);
        map.put("parent_id", parent_id);

        // 可选数据
        if (slug != null) {
        map.put("slug", slug);
        }
        if (description != null) {
        map.put("description", description);
        }
    }

    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public void setStruct(Map<String, Object> struct) {
        name = (String) struct.get("name");
        slug = (String) struct.get("slug");
        parent_id = (Integer) struct.get("parent_id");
        description = (String) struct.get("description");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
