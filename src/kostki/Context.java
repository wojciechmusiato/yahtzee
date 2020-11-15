package kostki;

public class Context {
    private final static Context instance = new Context();

    public static Context getInstance() {
        return instance;
    }

    private GameProperties gameProperties = new GameProperties();

    public GameProperties getGameProperties() {
        return gameProperties;
    }
}