package com.example.rdsingh;

public class GetClubs {

    String leaderName;
    String clubName;
    String sponsor;
    int maxSize;
    int size;
    int roomNum;

    public GetClubs(String clubName)
    {
        this.setClubName(clubName);
    }



    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }


}
