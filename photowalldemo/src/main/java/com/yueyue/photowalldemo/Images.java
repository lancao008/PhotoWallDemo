package com.yueyue.photowalldemo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yueyue on 2018/3/28 21:00
 * desc   :
 */

public class Images {


    @SerializedName("results")
    public List<ImageInfo> results=new ArrayList<>();


    public class ImageInfo {

        @SerializedName("url")
        public String url;

    }
}
