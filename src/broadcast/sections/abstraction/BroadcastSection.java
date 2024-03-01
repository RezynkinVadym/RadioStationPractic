package broadcast.sections.abstraction;

public abstract class BroadcastSection {
    private final int duration;
    private final String sectionType;
    protected BroadcastSection(int duration, String sectionType){
        this.duration = duration;
        this.sectionType = sectionType;
    }
    public int getDuration(){
        return duration;
    }
    public String getSectionType(){
        return sectionType;
    }
}
