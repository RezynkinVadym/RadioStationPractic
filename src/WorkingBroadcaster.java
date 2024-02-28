public class WorkingBroadcaster extends Broadcaster{
    private int workExperience;//work experience in years
    public WorkingBroadcaster(String name, String resume, int workExperience){
        super(name, resume);
        this.workExperience = workExperience;
        guest = false;
    }

    public void showInfo(){
        System.out.print("Work experience is " + workExperience + " years. ");
        super.showInfo();
    }

    public int getWorkExperience() {
        return workExperience;
    }
}
