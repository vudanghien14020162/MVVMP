package demo.exercise.pclap.demomvvpp.data.model;

/**
 * Created by PC Lap on 8/25/2017.
 */
public class ItemSongRepose {
    private String dataCode;
    private String tile;

    public ItemSongRepose(String dataCode, String tile) {
        this.dataCode = dataCode;
        this.tile = tile;
    }

    public String getDataCode() {
        return dataCode;
    }

    public String getTile() {
        return tile;
    }
}
