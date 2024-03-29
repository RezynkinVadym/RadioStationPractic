package broadcast;

import broadcast.broadcasters.Broadcaster;
import broadcast.sections.Advertising;
import broadcast.sections.Interview;
import broadcast.sections.Song;
import broadcast.sections.abstraction.BroadcastSection;

import java.time.LocalDate;
import java.util.*;

public class Broadcasting extends BroadcastingConst {
    private List<BroadcastSection> sections = new ArrayList<>();//список частин трансляції (пісні, реклами, інтерв'ю)
    private final LocalDate broadcastDate;
    private final int duration;//максимальна тривалість трансляції
    private final Broadcaster broadcaster;
    private double currentDuration = 0;//поточна тривалість трансляції, тобто сума тривалості всіх частин
    private double paidContentDuration = 0;//поточна тривалість платного контенту
    private int income = 0;//прибуток трансляції з платного контенту

    public Broadcasting(int duration, Broadcaster broadcaster, LocalDate broadcastDate){
        this.broadcastDate = broadcastDate;
        this.duration = duration;
        this.broadcaster = broadcaster;
        broadcaster.addBroadcasting(this);
    }

    public List<BroadcastSection> getSections(){ return sections; }
    public Broadcaster getBroadcaster(){ return broadcaster; }
    public int getDuration(){ return duration; }
    public LocalDate getDate() { return broadcastDate; }

    public String addSection(BroadcastSection section){
        double sectionDuration = section.getDuration();
        if(section.getSectionType().equals("Advertising"))
            sectionDuration /= 60;//приведення секунд реклами до хвилин

        if(checkDuration(sectionDuration)){
            if(isPaidContent(section)) {
                if(checkPaidContentDuration(sectionDuration)){
                    paidContentDuration += sectionDuration;
                    income += sectionIncome(section);
                }
                else
                    return "Paid content duration exceeded";
            }
            currentDuration += sectionDuration;
            sections.add(section);
            return "Success";
        }
        else
            return "Broadcasting duration exceeded";
    }

    public void showIncome(){
        if(income > 0)
            System.out.println("Profit from the broadcast is " + income + " euro(s)");
        else
            System.out.println("Broadcast has no profit");
    }

    public void shortInfo(){
        System.out.println("From " + broadcastDate + ", lasted " + duration + " minutes");
    }

    public void showInfo(){
        System.out.println("Broadcast duration " + duration + " minutes, broadcaster " + broadcaster.getName() + ", consists of the following parts:");
        Song song;
        Advertising advertising;
        Interview interview;
        String sectionInfo = "";
        for (BroadcastSection section : sections) {
            int sectionDuration = section.getDuration();
            switch (section.getSectionType()) {
                case "Song" -> {
                    song = (Song) section;
                    sectionInfo = "Song " + song.getTitle() + " by " + song.getPerformer() + ", " + sectionDuration + " minute(s)";
                }
                case "Advertising" -> {
                    advertising = (Advertising) section;
                    sectionInfo = advertising.getProduct() + " advertisement" + ", " + sectionDuration + " second(s)";
                }
                case "Interview" -> {
                    interview = (Interview) section;
                    sectionInfo = "Interview, respondent " + interview.getRespondent() + ", " + sectionDuration + " minute(s)";
                }
            }
            System.out.println(sectionInfo);
        }
    }

    private boolean checkDuration(double sectionDuration){//перевірка чи не буде перевищено максимальний
        return duration > sectionDuration + currentDuration;//час трансляції якщо додати нову частину
    }
    private boolean isPaidContent(BroadcastSection section){//перевірка чи є ця частина платним контентом
        return Arrays.asList(PAID_CONTENT_TYPES).contains(section.getSectionType());
    }
    private boolean checkPaidContentDuration(double sectionDuration){//чи не перевищує частина реклами допустимий коефіцієнт
        return ((paidContentDuration + sectionDuration) / duration) <= MAX_PAID_CONTENT_DURATION_COEF;
    }
    private int sectionIncome(BroadcastSection section){//розрахунок доходу від частини трансляції
        String sectionType = section.getSectionType();//залежно від її типу та тривалості
        int sectionDuration = section.getDuration();
        if(sectionType.equals("Advertising"))
            return sectionDuration * SECOND_ADVERTISING_COST;
        else if(sectionType.equals("Interview"))
            return sectionDuration * MINUTE_INTERVIEW_COST;
        return 0;
    }

}
