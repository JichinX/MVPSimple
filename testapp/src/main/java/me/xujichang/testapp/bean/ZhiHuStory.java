package me.xujichang.testapp.bean;

import java.util.ArrayList;

/**
 * 知乎标题：
 * example
 * <p>
 * {
 * title: "中国古代家具发展到今天有两个高峰，一个两宋一个明末（多图）",
 * ga_prefix: "052321",
 * images: [
 * "http://p1.zhimg.com/45/b9/45b9f057fc1957ed2c946814342c0f02.jpg"
 * ],
 * type: 0,
 * id: 3930445
 * }
 * Created by Administrator on 2017/2/22.
 */

public class ZhiHuStory {

    private String title;
    private String ga_prefix;
    private ArrayList<String> images;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private int type;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public ArrayList<String> getImages() {
        if (images == null) {
            images = new ArrayList<>();
        }
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ZhiHuStory{" +
                "title='" + title + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", images=" + images +
                ", type=" + type +
                ", id=" + id +
                '}';
    }
}
