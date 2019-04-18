package com.benpiotti.cs252lab6.Entity;

import java.util.Date;

public class Ticket {
    private int ticketid;
    private String sellerfirst;
    private String sellerlast;
    private double price;
    private String date;
    private boolean sold;
    private String gametime;
    private String buyerFirst;
    private String buyerLast;
    private String event;
    private String description;
    private String email;
    private String phone;

    public Ticket(int ticketid, String sellerfirst, String sellerlast, double price, String date, boolean sold, String gametime, String buyerFirst,
                  String buyerLast, String event, String description, String email, String phone) {
        this.ticketid = ticketid;
        this.sellerfirst = sellerfirst;
        this.sellerlast = sellerlast;
        this.price = price;
        this.date = date;
        this.sold = sold;
        this.gametime = gametime;
        this.buyerFirst = buyerFirst;
        this.buyerLast = buyerLast;
        this.event = event;
        this.description = description;
        this.email = email;
        this.phone = phone;
    }

    public Ticket() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicketid() {
        return ticketid;
    }

    public void setTicketid(int ticketid) {
        this.ticketid = ticketid;
    }

    public String getSellerfirst() {
        return sellerfirst;
    }

    public void setSellerfirst(String sellerfirst) {
        this.sellerfirst = sellerfirst;
    }

    public String getSellerlast() {
        return sellerlast;
    }

    public void setSellerlast(String sellerlast) {
        this.sellerlast = sellerlast;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public String getGametime() {
        return gametime;
    }

    public void setGametime(String gametime) {
        this.gametime = gametime;
    }

    public String getBuyerFirst() {
        return buyerFirst;
    }

    public void setBuyerFirst(String buyerFirst) {
        this.buyerFirst = buyerFirst;
    }

    public String getBuyerLast() {
        return buyerLast;
    }

    public void setBuyerLast(String buyerLast) {
        this.buyerLast = buyerLast;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketid=" + ticketid +
                ", sellerfirst='" + sellerfirst + '\'' +
                ", sellerlast='" + sellerlast + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", sold=" + sold +
                ", gametime='" + gametime + '\'' +
                ", buyerFirst='" + buyerFirst + '\'' +
                ", buyerLast='" + buyerLast + '\'' +
                ", event='" + event + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
