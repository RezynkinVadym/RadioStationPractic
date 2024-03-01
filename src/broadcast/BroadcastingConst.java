package broadcast;

public abstract class BroadcastingConst {
    protected final String[] PAID_CONTENT_TYPES = {"Advertising", "Interview"};//типи платного контенту
    protected final int SECOND_ADVERTISING_COST = 5;//константи цін за секунду реклами та хвилину інтерв'ю
    protected final int MINUTE_INTERVIEW_COST = 30;
    protected final double MAX_PAID_CONTENT_DURATION_COEF = 0.5;//максимально допустима доля платного контенту
}
