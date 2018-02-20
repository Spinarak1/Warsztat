package com.packt.project1.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GeneratorRaportu{

        private void dodajElementTekst (Document doc, Element rodzic, String nazwa, String tekst){
            Element e = doc.createElement(nazwa);
            e.appendChild(doc.createTextNode(tekst));
            rodzic.appendChild(e);
        }

        private void zapiszUsluge (Usluga u, Document doc, Element rodzic,int liczbaCzynnosci, int koszt){
            Element uslugaE = doc.createElement("usluga");
            rodzic.appendChild(uslugaE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            uslugaE.setAttribute("id", String.valueOf(u.getId()));
            dodajElementTekst(doc, uslugaE, "data_przyjecia", sdf.format(u.getDataPrzyjecia()));
            dodajElementTekst(doc, uslugaE, "liczba_czynnosci", String.valueOf(liczbaCzynnosci));
            dodajElementTekst(doc, uslugaE, "koszt", String.valueOf(koszt));
        }

        public void sumaKosztow (EntityManager entityManager, Date dataZakonczenia, String sciezka)throws Exception {

            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            String ds = d.format(dataZakonczenia);
            System.out.println(ds);
            Query query = entityManager.createQuery("SELECT k FROM Usluga k WHERE date(dataZakonczenia) = '" + ds + "'");
            @SuppressWarnings("unchecked")
			List<Usluga> uslugi = query.getResultList();


            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element uslugiE = doc.createElement("uslugi");
            doc.appendChild(uslugiE);
            uslugiE.setAttribute("data_zakonczenia", new SimpleDateFormat("yyyy-MM-dd").format(dataZakonczenia));

            for (Usluga u : uslugi) {
                System.out.println("id_uslugi: " + u.getId() + "id_klienta: " + u.getKlient().getId());
                Query czynnosciQuery = entityManager.createQuery("SELECT c FROM Czynnosc c WHERE usluga_id = " + u.getId());
				@SuppressWarnings("unchecked")
				List<Czynnosc> czynnosci = czynnosciQuery.getResultList();
                int sumaKosztow = 0;
                for (Czynnosc c : czynnosci) {
                    sumaKosztow += c.getKwota();
                }
                System.out.println("Suma kosztow: " + sumaKosztow);
                zapiszUsluge(u, doc, uslugiE, czynnosci.size(), sumaKosztow);
            }


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(sciezka));
            transformer.transform(source, result);
        }
        public void sumaKosztowZaWczoraj (EntityManager entityManager, String katalog)throws Exception {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            Date data = calendar.getTime();
            String nazwaPliku = katalog + new SimpleDateFormat("yyyy-MM-dd").format(data) + ".xml";
            System.out.println(nazwaPliku);
            sumaKosztow(entityManager, data, nazwaPliku);
        }
}
