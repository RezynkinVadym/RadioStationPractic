package broadcast.sections;

import broadcast.sections.abstraction.BroadcastSection;

public class Advertising extends BroadcastSection {
    private final String product;

    public Advertising(String product, int durationSeconds) {
        super(durationSeconds, "Advertising");
        this.product = product;
    }
    public String getProduct(){
        return product;
    }
}
