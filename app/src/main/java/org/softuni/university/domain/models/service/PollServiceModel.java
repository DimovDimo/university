package org.softuni.university.domain.models.service;

import org.modelmapper.ModelMapper;
import org.softuni.university.domain.entities.Poll;
import org.softuni.university.mappings.IHaveCustomMappings;

public class PollServiceModel implements IHaveCustomMappings {

    private String id;
    private String liking;
    private String notLiking;
    private String easiest;
    private String mostDifficult;
    private String whatYouWantChanges;
    private String whoYouSaid;
    private String yourWork;
    private String whatEmployersAreLookingFor;
    private String student;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiking() {
        return liking;
    }

    public void setLiking(String liking) {
        this.liking = liking;
    }

    public String getNotLiking() {
        return notLiking;
    }

    public void setNotLiking(String notLiking) {
        this.notLiking = notLiking;
    }

    public String getEasiest() {
        return easiest;
    }

    public void setEasiest(String easiest) {
        this.easiest = easiest;
    }

    public String getMostDifficult() {
        return mostDifficult;
    }

    public void setMostDifficult(String mostDifficult) {
        this.mostDifficult = mostDifficult;
    }

    public String getWhatYouWantChanges() {
        return whatYouWantChanges;
    }

    public void setWhatYouWantChanges(String whatYouWantChanges) {
        this.whatYouWantChanges = whatYouWantChanges;
    }

    public String getWhoYouSaid() {
        return whoYouSaid;
    }

    public void setWhoYouSaid(String whoYouSaid) {
        this.whoYouSaid = whoYouSaid;
    }

    public String getYourWork() {
        return yourWork;
    }

    public void setYourWork(String yourWork) {
        this.yourWork = yourWork;
    }

    public String getWhatEmployersAreLookingFor() {
        return whatEmployersAreLookingFor;
    }

    public void setWhatEmployersAreLookingFor(String whatEmployersAreLookingFor) {
        this.whatEmployersAreLookingFor = whatEmployersAreLookingFor;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        mapper.createTypeMap(Poll.class, PollServiceModel.class)
                .addMapping(
                        poll -> poll.getUser().getUsername(),
                        (dto, value) -> dto.setStudent((String) value)
                );
    }
}
