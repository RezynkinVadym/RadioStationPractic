package broadcast.sections;

import broadcast.sections.abstraction.BroadcastSection;

public class Interview extends BroadcastSection {
    private final String respondent;

    public Interview(String respondent, int durationMinutes) {
        super(durationMinutes, "Interview");
        this.respondent = respondent;
    }
    public String getRespondent(){
        return respondent;
    }
}
