package com.example.natanael.fatec_saude;

public class Marker {private Double latitude;
    private Double longitude;
    private String nomes;
    private String moradas1;
    private String moradas2;
    private String codPostal2;
    private String images;

    public Marker(Double lat, Double lng, String nome,String morada1, String morada2, String codPostal, String image){

        latitude = lat;
        longitude = lng;
        nomes = nome;
        moradas1 = morada1;
        moradas2 =morada2;
        codPostal2=codPostal;
        images=image;
    }

    public Double getLat(){
        return latitude;
    }

    public Double getLon(){
        return longitude;
    }

    public String getNome() { return nomes; }
    public String getMorada1() { return moradas1; }
    public String getMorada2() { return moradas2; }
    public String getCodPostal() { return codPostal2; }
    public String getImage() { return images; }
}

