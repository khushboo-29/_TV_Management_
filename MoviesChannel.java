public class MoviesChannel extends TvChannels{
    int additionalfee=15;
    public MoviesChannel(String channelName, String language, String category, int price) {
        super(channelName, language, category, price);
    }

    @Override
    public int getPrice() {
        return super.getPrice()+additionalfee;
    }}

