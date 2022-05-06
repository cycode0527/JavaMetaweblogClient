package top.sxrhhh.metaweblogClient.struct;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
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

    public FileData() {
    }

    /**
     *
     * @author Sxrhhh
     * 2022/5/6 18:19
     * @param file 存入的文件对象(自动配置属性)
     */
    public FileData(File file) {
        if (file != null) {
            this.initFile(file);
        }

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
