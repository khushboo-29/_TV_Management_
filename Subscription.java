import java.io.Serializable;

public class Subscription implements Serializable {
    private int installFee;     //fixed for tv
    private int nbtv;
    private Subscriber subscriber;
    private SubscriptionCycle cycle;
    private String datee;

    private  int totalFee;


    public Subscription(int nbtv, Subscriber subscriber, SubscriptionCycle cycle, String datee) {
        this.nbtv = nbtv;
        this.subscriber = subscriber;
        this.cycle = cycle;
        this.datee = datee;

        this.totalFee=nbtv * 10;
    }

    public int getNbtv() {
        return nbtv;
    }

    public void setNbtv(int nbtv) {
        this.nbtv = nbtv;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public SubscriptionCycle getCycle() {
        return cycle;
    }

    public void setCycle(SubscriptionCycle cycle) {
        this.cycle = cycle;
    }

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public int getTotalFee() {
        totalFee=installFee+5;
        return totalFee;
    }


    @Override
    public String toString() {
        return "Subscription{" +
                "installFee=" + installFee +
                ", nbtv=" + nbtv +
                ", subscriber=" + subscriber +
                ", cycle=" + cycle +
                ", datee='" + datee + '\'' +
                ", totalFee=" + totalFee +
                '}';
    }
}
