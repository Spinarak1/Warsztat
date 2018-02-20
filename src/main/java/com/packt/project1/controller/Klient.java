package com.packt.project1.controller;
import javax.persistence.*;


	@Entity
	@Table(name = "Klient")
	public class Klient {

	    @Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;
	    @Column(name = "imie")
	    private String imie;
	    @Column(name = "nazwisko")
	    private String nazwisko;
	    @Column (name = "adres")
	    private String adres;
	    @Column (name = "telefon")
	    private String telefon;

	    public Klient(){}
	    public Klient(String imie, String nazwisko, String adres, String telefon) {
	        this.imie = imie;
	        this.nazwisko = nazwisko;
	        this.adres = adres;
	        this.telefon = telefon;
	    }
	    public Klient(int id, String imie, String nazwisko, String adres, String telefon) {
	    	this.id = id;
	        this.imie = imie;
	        this.nazwisko = nazwisko;
	        this.adres = adres;
	        this.telefon = telefon;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getImie() {
	        return imie;
	    }

	    public void setImie(String imie) {
	        this.imie = imie;
	    }

	    public String getNazwisko() {
	        return nazwisko;
	    }

	    public void setNazwisko(String nazwisko) {
	        this.nazwisko = nazwisko;
	    }

	    public String getAdres() {
	        return adres;
	    }

	    public void setAdres(String adres) {
	        this.adres = adres;
	    }

	    public String getTelefon() {
	        return telefon;
	    }

	    public void setTelefon(String telefon) {
	        this.telefon = telefon;
	    }
	}

