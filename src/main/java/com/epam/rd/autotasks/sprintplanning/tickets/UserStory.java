package com.epam.rd.autotasks.sprintplanning.tickets;


import java.util.Arrays;

public class UserStory extends Ticket {
    private UserStory[] dependencies;

    public UserStory(int id, String name, int estimate, UserStory... dependsOn) {
        super(id, name, estimate);
        this.dependencies = dependsOn;
    }

    @Override
    public void complete() {
        boolean allDependenciesCompleted = true;
        for (UserStory dependency : dependencies) {
            if (!dependency.isCompleted()) {
                allDependenciesCompleted = false;
                break;
            }
        }
        if (allDependenciesCompleted) {
            super.complete();
        }
    }

    public UserStory[] getDependencies() {

        return Arrays.copyOf(dependencies, dependencies.length);
    }

    @Override
    public String toString() {
        return "[US " + getId() + "] " + getName();
    }
}

