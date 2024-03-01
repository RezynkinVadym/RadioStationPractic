package broadcast.broadcasters;

import broadcast.Broadcasting;

import java.util.ArrayList;
import java.util.List;

public class Broadcaster {
    protected boolean guest = true;
    private final String name;
    private List<Broadcasting> broadcastings = new ArrayList<>();
    private final String resume;
    public Broadcaster(String name, String resume){
        this.name = name;
        this.resume = resume;
    }
    public List<Broadcasting> getBroadcastingList(){
        return broadcastings;
    }
    public String getName(){
        return name;
    }
    public boolean isGuest(){
        return guest;
    }
    public void addBroadcasting(Broadcasting broadcasting){
        broadcastings.add(broadcasting);
    }
    public void showInfo(){
        System.out.print("Broadcaster " + name + ", " + resume);
        if(broadcastings.size() != 0)
            System.out.println(", conducted following broadcasts:");
        else
            System.out.println();
        for (Broadcasting broadcasting : broadcastings) {
            System.out.println("From " + broadcasting.getDate() + ", duration " + broadcasting.getDuration());
        }
    }
}
