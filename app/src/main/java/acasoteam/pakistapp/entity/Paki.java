package acasoteam.pakistapp.entity;

/**
 * Created by andre on 18/12/2016.
 */

public class Paki {


    private int idPaki;
    private String name;
    private String address;
    private double lat;
    private double lon;
    private double avgRate;
    private int numVote;


    public Paki (int idPaki, String name, String address, double lat, double lon, double avgRate, int numVote){
        this.idPaki = idPaki;
        this.name = name;
        this.address = address;
        this.lon = lon;
        this.lat = lat;
        this.avgRate = avgRate;
        this.numVote = numVote;
    }

    public int getIdPaki() {
        return idPaki;
    }

    public void setIdPaki(int idPaki) {
        this.idPaki = idPaki;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    public int getNumVote() {
        return numVote;
    }

    public void setNumVote(int numVote) {
        this.numVote = numVote;
    }


}
