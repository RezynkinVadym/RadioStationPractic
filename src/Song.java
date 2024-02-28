public class Song extends BroadcastSection{
    private final String title;
    private final String performer;

    public Song(String title, String performer, int durationMinutes) {
        super(durationMinutes, "Song");
        this.title = title;
        this.performer = performer;
    }
    public String getTitle(){
        return title;
    }
    public String getPerformer(){
        return performer;
    }
}
