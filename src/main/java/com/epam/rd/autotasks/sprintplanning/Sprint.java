package com.epam.rd.autotasks.sprintplanning;

import com.epam.rd.autotasks.sprintplanning.tickets.Bug;
import com.epam.rd.autotasks.sprintplanning.tickets.Ticket;
import com.epam.rd.autotasks.sprintplanning.tickets.UserStory;

import java.util.ArrayList;
import java.util.List;

public class Sprint {
    private int timeCapacity;
    private int ticketsLimit;
    private ArrayList<Ticket> tickets;

    public Sprint(int timeCapacity, int ticketsLimit) {
        this.timeCapacity = timeCapacity;
        this.ticketsLimit = ticketsLimit;
        this.tickets = new ArrayList<>();
    }

    public boolean addUserStory(UserStory userStory) {
        if (userStory == null || userStory.isCompleted()) {
            return false;
        }

        for (UserStory dependency : userStory.getDependencies()) {
            if (!dependency.isCompleted()) {
                return false;
            }
        }

        if (this.tickets.size() >= this.ticketsLimit ||
                this.getTotalEstimate() + userStory.getEstimate() > this.timeCapacity) {
            return false;
        }

        this.tickets.add(userStory);
        return true;
    }

    public boolean addBug(Bug bugReport) {
        if (bugReport == null || bugReport.isCompleted()) {
            return false;
        }

        if (this.tickets.size() >= this.ticketsLimit ||
                this.getTotalEstimate() + bugReport.getEstimate() > this.timeCapacity) {
            return false;
        }

        this.tickets.add(bugReport);
        return true;
    }

    public Ticket[] getTickets() {
        return this.tickets.toArray(new Ticket[0]);
    }

    public int getTotalEstimate() {
        int total = 0;
        for (Ticket ticket : this.tickets) {
            total += ticket.getEstimate();
        }
        return total;
    }
}