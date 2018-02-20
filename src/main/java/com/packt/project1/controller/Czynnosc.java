package com.packt.project1.controller;
import javax.persistence.*;
//fetch=FetchType.LAZY
@Entity
@Table(name = "Czynnosc")
public class Czynnosc {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "kwota")
    private int kwota;
    @Column(name = "opis")
    private String opis;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Usluga usluga;

    public Czynnosc(){}
    public Czynnosc(Usluga usluga, int kwota, String opis ){
        this.usluga = usluga;
        this.kwota = kwota;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getKwota() {
        return kwota;
    }
    public void setKwota(int kwota){
        this.kwota = kwota;
    }
    public String getOpis() {
        return opis;
    }
    public void setOpis(String opis){
        this.opis = opis;
    }

    public Usluga getUsluga() {
        return usluga;
    }
    public void setUsluga(Usluga usluga){
        this.usluga = usluga;
    }
}
