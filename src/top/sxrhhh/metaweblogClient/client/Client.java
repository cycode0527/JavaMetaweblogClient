package top.sxrhhh.metaweblogClient.client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import top.sxrhhh.metaweblogClient.struct.FileData;
import top.sxrhhh.metaweblogClient.struct.Post;
import top.sxrhhh.metaweblogClient.struct.UrlData;
import top.sxrhhh.metaweblogClient.struct.WpCategory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * metaweblog的客户端,负责进行xmlrpc的沟通,拥有许多方法可供直接使用.
 * <p>使用方法:(以新建一个博客文章为例)<br>
 * 1. 你要创建一个Client对象:<br>
 * 2. 准备好参数<br>
 * 3. 上传命令,并处理异常<br><br>
 * 代码示例:
 * <pre>
 *     {@code
 *        public static void newPostTest() {
 *            // 准备好命令所需参数(新建Post对象)
 *            Post post = new Post(new Date(), "# This is a post\n> You can see the Post\n", "Test");
 *            // 准备好返回值(自己看方法注释的返回类型)
 *            String result = null;
 *            // 创建连接客户端
 *            try {   // 自己解决抛出的异常
 *                Client client = new Client("https://www.cycode.club/xmlrpc.php");
 *                result = client.newPost("default", "S*******u", "******", post, false);
 *            } catch (MalformedURLException e) {
 *                e.printStackTrace();    // 一般为URL格式错误
 *            } catch (XmlRpcException e) {
 *                e.printStackTrace();    // 一般为参数不全、服务器错误、URL输入错误
 *            }
 *            // 输出结果
 *            System.out.println(result);
 *        }
 *     }
 * </pre>
 * @author Sxrhhh
 * 创建于: 2022/5/5 12:38
 * @version 1.0
 * @since 1.8
 *
 * @see top.sxrhhh.metaweblogClient.struct.BlogInfo
 * @see top.sxrhhh.metaweblogClient.struct.WpCategory
 * @see top.sxrhhh.metaweblogClient.struct.Post
 * @see top.sxrhhh.metaweblogClient.struct.UrlData
 * @see top.sxrhhh.metaweblogClient.struct.FileData
 * @see top.sxrhhh.metaweblogClient.struct.CategoryInfo
 */
public class Client {

    private URL blogUrl;
    private XmlRpcClient client;
    private XmlRpcClientConfigImpl config;


    /**
     * 通过URL对象创建Client.
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
     * 通过字符串url创建Client.
     * @param url 博客metaweblog的api地址url
     * @throws MalformedURLException URL格式不对
     */
    public Client(String url) throws MalformedURLException {
        client = new XmlRpcClient();
        this.blogUrl = new URL(url); // 设置了blog的url
        initConfig();
    }

    /**
     * 删除一篇文章.
     * <p>通常,我們需要获取appkey才能使用此方法,<br>
     * 这跟网站本身有关,通常不常用<br>
     * (悠着点,好好去网站上亲自删除博客).
     *
     * @author Sxrhhh
     * 2022/5/5 13:27
     * @param appKey 网站key
     * @param postid 文章id
     * @param username 博客用户名
     * @param password 博客密码
     * @param publish 是否重新发布博客
     * @return boolean 永远为true(可能)
     * @throws XmlRpcException 可能为参数不全或博客api地址不对
     */
    public boolean deletePost(String appKey, String postid, String username, String password, boolean publish) throws XmlRpcException {
        Object[] params = new Object[]{appKey, postid, username, password, publish};
        Object result = null;
        result = client.execute("blogger.deletePost", params);

        return (boolean) result;

    }

    /**
     * 获取一篇博客内容.
     * <p>前排提醒,本方法可能与wordpress中的部分插件不兼容.
     * 已知不兼容插件:editor.md(找不到IXR_Message类).
     * <p>示例:<br>
     * <pre>
     *     {@code
     *      Post result = client.getPost("2178", "LiHua", "LihuaPasswd");
     *      System.out.println(result.getDescription());
     *     }
     * </pre>
     * @param postid 文章ID
     * @param username 用户名
     * @param password 密码
     * @return Post 返回获取的文章对象
     * @throws XmlRpcException <br>
     * 与wordpress的editor.md不兼容<br>
     * 可能为参数不全或博客api地址不对
     */
    public Post getPost(String postid, String username, String password) throws XmlRpcException {
        Post post = new Post();
        Object[] params = new Object[]{postid, username, password};
        Object result = client.execute("metaWeblog.getPost", params);
        if (result != null) {
            post.setStruct((Map<String, Object>) result);
        }
        return post;

    }

    /**
     * 新建一个媒体(上传媒体).
     * <p>向博客的媒体库中上传一个媒体,通常为图片和视频文件,其他的应该也可以,
     * 遵循Internet的MIME类型,方便上传文件(比较重要).
     * <p>示例:
     * <pre>
     *     {@code
     *      FileData file = new FileData(new File("D:/test.gif"));
     *      UrlData result = client.newMediaObject("default", "LiHua", "LihuaPasswd", file);
     *      System.out.println(result.getUrl());
     *     }
     * </pre>
     * @author Sxrhhh
     * 2022/5/6 13:02
     * @param blogid 对于单站点,默认为"default" 或是 "0"
     * @param username 用户名
     * @param password 密码
     * @param file FileData类
     * @return UrlData 返回新媒体的url(为了保证一致,选择了UrlData类)
     * @throws XmlRpcException 可能为参数不全或博客api地址不对
     */
    public UrlData newMediaObject(String blogid, String username, String password, FileData file) throws XmlRpcException {
        Object[] params = new Object[]{blogid, username, password, file.getStruct()};
        Object result = null;
        UrlData url = new UrlData();
        result = client.execute("metaWeblog.newMediaObject", params);
        if (result != null) {
        url.setStruct((Map<String, Object>) result);
        }
        return url;
    }

    /**
     * 新建一篇博客文章.
     * <p>向网站上上传一篇博客,以纯文本字符串形式,可以为markdown文件的内容<br>
     * 返回新建文本的Postid.(比较重要)
     * <p>示例:(见本类自身文档获取更多信息)
     * <pre>
     *     {@code
     *      Post post = new Post(new Date(), "# This is a post\n> You can see the Post\n", "Test");
     *      String result = client.newPost("default", "LiHua", "LiHuaPasswd", post, true);
     *      System.out.println(result);
     *     }
     * </pre>
     * @author Sxrhhh
     * 2022/5/5 13:48
     * @param blogid 默认为"default"
     * @param username 用户名
     * @param password 密码
     * @param post 文章内容对象
     * @param publish 是否发布
     * @return String 返回postid
     * @throws XmlRpcException 可能为参数不全或博客api地址不对
     */
    public String newPost(String blogid, String username, String password, Post post, boolean publish) throws XmlRpcException {
        Object[] params = new Object[]{blogid, username, password, post.getStruct(), new Boolean(publish)};
        Object result = null;
        result = client.execute("metaWeblog.newPost", params);

        return (String) result;


    }

    /**
     * 新建一个分类.
     * <p>向网站要求新建一个分类(使用{@code WpCategory}类),返回它的ID
     * <p>示例:
     * <pre>
     *     {@code
     *      WpCategory cate = new WpCategory("java学习路线", 0);
     *      Integer result = client.newCategory("default", "LiHua", "LihuaPasswd", cate);
     *      System.out.println(result);
     *     }
     * </pre>
     * @author Sxrhhh
     * 2022/5/6 18:24
     * @param blog_id 默认为"default" 或 "0"
     * @param username 博客用户名
     * @param password 博客密码
     * @param category 新建的分类信息
     * @return Integer 创建的分类ID
     * @throws XmlRpcException 可能为参数不全或博客api地址不对
     */
    public Integer newCategory(String blog_id, String username, String password, WpCategory category) throws XmlRpcException {
        Object[] params = new Object[]{blog_id, username, password, category.getStruct()};
        Object result = null;
        result = client.execute("wp.newCategory", params);

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
     * 设置了本对象客户端和服务器(为客户传入的url).
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
