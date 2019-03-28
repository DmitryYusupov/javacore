package ru.yusdm.javacore.lesson15up16concurrency.autoservice.model.search;

public class PassengerModelSearchCondition extends ModelSearchCondition {
    private Integer numberOfAirbags;
    private Integer numberOfSeats;
    private String audioSystemName;

    public boolean searchByNumberOfAirBags() {
        return numberOfAirbags != null;
    }

    public boolean searchByNumberOfSeats() {
        return numberOfSeats != null;
    }

    public boolean searchByAudioSystemName() {
        return audioSystemName != null && !audioSystemName.isEmpty();
    }

    public Integer getNumberOfAirbags() {
        return numberOfAirbags;
    }

    public void setNumberOfAirbags(Integer numberOfAirbags) {
        this.numberOfAirbags = numberOfAirbags;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getAudioSystemName() {
        return audioSystemName;
    }

    public void setAudioSystemName(String audioSystemName) {
        this.audioSystemName = audioSystemName;
    }
}
