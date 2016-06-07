package models;

/**
 * Created by minhnguy on 01.06.2016.
 */
public class Broker {

    private String id;

    private String game;

    private String estates;

    public Broker(String game, String estates) {
        this.game = game;
        this.estates = estates;
        String[] strArr = game.split("/");
        this.id = strArr[strArr.length - 1];
    }

    public String getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public String getEstates() {
        return estates;
    }
}
