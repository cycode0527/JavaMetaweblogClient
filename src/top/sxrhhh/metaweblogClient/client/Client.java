package top.sxrhhh.metaweblogClient.client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import top.sxrhhh.metaweblogClient.struct.FileData;
import top.sxrhhh.metaweblogClient.struct.Post;
import top.sxrhhh.metaweblogClient.struct.UrlData;
import top.sxrhhh.metaweblogClient.struct.WpCategory;

import java.net.URL;
import java.util.Map;

/**
 * metaweblog的客户端,负责进行xmlrpc的沟通,拥有许多方法可供直接使用
 * @author Sxrhhh
 * 2022/5/5 12:38
 * @version 1.0
 * @since 1.8
 */
public class Client {

    private URL blogUrl;
    private XmlRpcClient client;
    private XmlRpcClientConfigImpl config;


    /**
     * 客户端构造方法,利用他可以进行数据传输
     * @author Sxrhhh
     * 2022/5/5 13:00
     * @param blogUrl 博客metaweblog的api地址url
     */
    public Client(URL blogUrl) {
        client = new XmlRpcClient();
        this.blogUrl = blogUrl; // 设置了blog的url
        initConfig();
    }

    /**
     * 删除一篇博客
     * @author Sxrhhh
     * 2022/5/5 13:27
     * @param appKey 网站key
     * @param postid 文章id
     * @param username 博客用户名
     * @param password 博客密码
     * @param publish 是否重新发布博客
     * @return boolean 永远为true(可能)
     */
    public boolean deletePost(String appKey, String postid, String username, String password, boolean publish) {
        Object[] params = new Object[]{appKey, postid, username, password, publish};
        Object result = null;
        try {

            result = client.execute("blogger.deletePost", params);

        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return (boolean) result;

    }

    /**
     * TODO
     * @author Sxrhhh
     * 2022/5/6 18:30
     * @param postid 文章ID
     * @param username 用户名
     * @param password 密码
     * @return top.sxrhhh.metaweblogClient.struct.Post
     */
    public Post getPost(String postid, String username, String password) {
        Object[] params = new Object[]{postid, username, password};
        Object result = null;

//        result = client.execute("metaWeblog.getPost", )
    }

    /**
     * 新建一个媒体(上传媒体)
     * @author Sxrhhh
     * 2022/5/6 13:02
     * @param blogid 默认为"default"
     * @param username 用户名
     * @param password 密码
     * @param file FileData类
     * @return UrlData 返回新媒体的url(为了保证一致,选择了UrlData类)
     */
    public UrlData newMediaObject(String blogid, String username, String password, FileData file) {
        Object[] params = new Object[]{blogid, username, password, file.getStruct()};
        Object result = null;
        UrlData url = new UrlData();
        try {
            result = client.execute("metaWeblog.newMediaObject", params);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        if (result != null) {
        url.setStruct((Map<String, Object>) result);
        }
        return url;
    }

    /**
     *
     * @author Sxrhhh
     * 2022/5/5 13:48
     * @param blogid 默认为"default"
     * @param username 用户名
     * @param password 密码
     * @param post 文章内容对象
     * @param publish 是否发布
     * @return String 返回postid
     */
    public String newPost(String blogid, String username, String password, Post post, boolean publish) {
        Object[] params = new Object[]{blogid, username, password, post.getStruct(), new Boolean(publish)};
        Object result = null;
        try {
            result = client.execute("metaWeblog.newPost", params);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }

        return (String) result;


    }

    /**
     * 新建一个分类
     * @author Sxrhhh
     * 2022/5/6 18:24
     * @param blog_id 默认为"default"
     * @param username 博客用户名
     * @param password 博客密码
     * @param category 新建的分类信息
     * @return Integer 创建的分类ID
     */
    public Integer newCategory(String blog_id, String username, String password, WpCategory category) {
        Object[] params = new Object[]{blog_id, username, password, category.getStruct()};
        Object result = null;
        try {
            result = client.execute("wp.newCategory", params);
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
        return (Integer) result;
    }


    public URL getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(URL blogUrl) {
        this.blogUrl = blogUrl;
    }

    public XmlRpcClient getClient() {
        return client;
    }

    public void setClient(XmlRpcClient client) {
        this.client = client;
    }

    public XmlRpcClientConfigImpl getConfig() {
        return config;
    }

    public void setConfig(XmlRpcClientConfigImpl config) {
        this.config = config;
    }

    /**
     * 设置了本对象客户端和服务器(为客户传入的url)
     * @author Sxrhhh
     * @date 2022/5/5 13:04
     */
    private void initConfig() {
        if (blogUrl != null) {
            config = new XmlRpcClientConfigImpl();
            config.setServerURL(this.blogUrl);
            client.setConfig(config);
        }
    }
}
