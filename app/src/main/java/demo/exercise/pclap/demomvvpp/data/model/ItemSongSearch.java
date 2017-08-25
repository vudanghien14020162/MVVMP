package demo.exercise.pclap.demomvvpp.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PC Lap on 8/25/2017.
 */
public class ItemSongSearch {
    //Tên tuần tự
    @SerializedName("Id")

    private String Id;

    @SerializedName("Title")

    private String Title;

    @SerializedName("Artist")

    private String Artist;

    @SerializedName("Avatar")

    private String Avatar;

    @SerializedName("UrlSource")

    private String UrlSource;

    @SerializedName("SiteId")

    private String SiteId;

    @SerializedName("HostName")

    private String HostName;

    public ItemSongSearch(String id, String title, String artist, String avatar, String urlSource, String siteId, String hostName) {
        Id = id;
        Title = title;
        Artist = artist;
        Avatar = avatar;
        UrlSource = urlSource;
        SiteId = siteId;
        HostName = hostName;
    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return Title;
    }

    public String getArtist() {
        return Artist;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getUrlSource() {
        return UrlSource;
    }

    public String getSiteId() {
        return SiteId;
    }

    public String getHostName() {
        return HostName;
    }
}
