package top.sxrhhh.metaweblogClient.struct;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

/**
 * 文件数据类,建议使用带参构造函数
 * @author Sxrhhh
 * 2022/5/3 23:55
 * @version 1.0
 * @since 1.8
 */
public class FileData extends Struct {
    private byte[] bits;
    private String name;
    private String type;

    /**
     * 空构造方法.
     * @deprecated 处理一个文件数据,还要读取成字节过于复杂
     */
    public FileData() {}

    /**
     * 文件类构造方法
     * <p>直接将File文件存入FileData类中<br>
     * <b>强烈推荐!!!</b>
     * @author Sxrhhh
     * 2022/5/6 18:19
     * @param file 存入的文件对象(自动配置属性)
     */
    public FileData(File file) {
        if (file != null) {
            this.initFile(file);
        }

    }

    /**
     * 直接通过结构体struct对象创建的构造方法.
     * @param struct 传入一个Map对象
     */
    public FileData(Map<String, Object> struct) {
        this.setStruct(struct);
    }

    @Override
    public void initMap() {
        super.initMap();

        // 将数据塞入map中
        map.put("bits", bits);
        map.put("name", name);
        map.put("type", type);
    }

    /**
     * @param struct 作为Struct的Map对象
     * @author Sxrhhh
     * 2022/5/3 22:47
     */
    @Override
    public void setStruct(Map<String, Object> struct) {
        bits = (byte[]) struct.get("bits");
        name = (String) struct.get("name");
        type = (String) struct.get("type");
    }

    public byte[] getBits() {
        return bits;
    }

    public void setBits(byte[] bits) {
        this.bits = bits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @author Sxrhhh
     * 2022/5/6 12:17
     * @param file 传入文件,可以自动存储名字与Base64
     */
    private void initFile(File file) {
        // 获取文件类型
        Path path = file.toPath();
        try {
            this.type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 获取名字
        this.name = file.getName();
        // 获取内容
        bits = readFileByBytes(file);
    }

    /**
     *
     * @author Sxrhhh
     * 2022/5/6 12:51
     * @param file 传入文件
     * @return byte[] 返回字节数组对象
     */
    private static byte[] readFileByBytes(File file) {
        if (file == null) {
            return null;
        }
        int length = (int) file.length();
        byte[] data = new byte[length];
            try {
                new FileInputStream(file).read(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
       return data;
    }

}
