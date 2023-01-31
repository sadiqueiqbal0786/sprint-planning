package com.epam.rd.autotasks.sprintplanning.tickets;

public class Bug extends Ticket {
    private UserStory relatedUserStory;

    private Bug(int id, String name, int estimate, UserStory relatedUserStory) {
        super(id, name, estimate);
        this.relatedUserStory = relatedUserStory;
    }

    public static Bug createBug(int id, String name, int estimate, UserStory userStory) {
        if (userStory == null || !userStory.isCompleted()) {
            return null;
        }
        return new Bug(id, name, estimate, userStory);
    }

    @Override
    public String toString() {
        return "[Bug " + getId() + "] " + relatedUserStory.getName() + ": " + getName();
    }
}
