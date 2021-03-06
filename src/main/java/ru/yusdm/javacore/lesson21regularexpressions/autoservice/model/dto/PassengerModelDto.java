package ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.dto;

import ru.yusdm.javacore.lesson21regularexpressions.autoservice.model.domain.ModelDiscriminator;

public class PassengerModelDto extends ModelDto {
    private int numberOfAirbags;
    private int numberOfSeats;
    private String audioSystemName;

    public int getNumberOfAirbags() {
        return numberOfAirbags;
    }

    public void setNumberOfAirbags(int numberOfAirbags) {
        this.numberOfAirbags = numberOfAirbags;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getAudioSystemName() {
        return audioSystemName;
    }

    public void setAudioSystemName(String audioSystemName) {
        this.audioSystemName = audioSystemName;
    }

    @Override
    protected void initDiscriminator() {
        discriminator = ModelDiscriminator.PASSENGER;
    }

    @Override
    public String toString() {
        return super.toString() +
                "numberOfAirbags=" + numberOfAirbags +
                ", numberOfSeats=" + numberOfSeats +
                ", audioSystemName='" + audioSystemName + '\'';
    }
}
