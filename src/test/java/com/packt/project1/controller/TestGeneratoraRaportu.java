package com.packt.project1.controller;
import java.io.File;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.SimpleDateFormat;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class TestGeneratoraRaportu
{
    @Test
    public void testujRaportowanie(){
        EntityManager entityManager = null;

        EntityManagerFactory entityManagerFactory = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate-dynamic");
            entityManager = entityManagerFactory.createEntityManager();

            utworzBaze(entityManager);
            GeneratorRaportu generator = new GeneratorRaportu();
            generator.sumaKosztow(entityManager, new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-11"), "C:\\Users\\patkrz2\\Desktop\\Test.xml");
            entityManager.close();

            File inputFile = new File("C:\\Users\\patkrz2\\Desktop\\Test.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            Element uslugiE = doc.getDocumentElement();
            Assert.assertEquals("uslugi", uslugiE.getNodeName());
            NodeList uslugaL = uslugiE.getElementsByTagName("usluga");
            Assert.assertEquals(4, uslugaL.getLength());
            Element uslugaE0 = (Element)uslugaL.item(0);
            Assert.assertEquals("1", uslugaE0.getAttribute("id"));
            NodeList dataPrzyjeciaL0 = uslugaE0.getElementsByTagName("data_przyjecia");
            Assert.assertEquals(1, dataPrzyjeciaL0.getLength());
            Element dataPrzyjeciaE0 = (Element)dataPrzyjeciaL0.item(0);
            Assert.assertEquals("2017-05-19", dataPrzyjeciaE0.getTextContent());

        } catch (Throwable ex) {
            ex.printStackTrace();
            Assert.assertEquals(1, 0);
        } finally {
            entityManagerFactory.close();
        }
    }
    private void utworzBaze(EntityManager entityManager){
    try {
        entityManager.getTransaction().begin();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Klient kl1 = new Klient("Pawe³", "Jankowski", "Polna 1 Poznan", "783745645");
        entityManager.persist(kl1);
        Usluga us1 = new Usluga(kl1, "wymiana dr¹¿ków", format.parse("2017-05-19"), format.parse("2018-01-11"));
        entityManager.persist(us1);
        Czynnosc cz1 = new Czynnosc(us1, 150, "wymiana dr¹¿ków");
        entityManager.persist(cz1);
        Czynnosc cz2 = new Czynnosc(us1, 100, "Geometria kó³");
        entityManager.persist(cz2);
        Czynnosc cz3 = new Czynnosc(us1, 300, "Wymiana sprêg³a");
        entityManager.persist(cz3);
        Usluga us2 = new Usluga(kl1, "rutynowe badanie techniczne", format.parse("2017-06-21"), format.parse("2018-01-11"));
        entityManager.persist(us2);
        Czynnosc cz4 = new Czynnosc(us2, 150, "Wymiana oleju");
        entityManager.persist(cz4);
        Czynnosc cz5 = new Czynnosc(us2, 1200, "Wymiana kompletu rozrzadu");
        entityManager.persist(cz5);
        Klient kl2 = new Klient("Adam", "Igiel", "Lipowa Sulecin", "673763812");
        entityManager.persist(kl2);
        Usluga us3 = new Usluga(kl2, "naprawa 3", format.parse("2017-12-23"), format.parse("2018-01-11"));
        entityManager.persist(us3);
        Czynnosc cz6 = new Czynnosc(us3, 1500, "Wymiana skrzyni biegów");
        entityManager.persist(cz6);
        Czynnosc cz7 = new Czynnosc(us3, 20, "Uzupe³nienie plynu chlodniczego");
        entityManager.persist(cz7);
        Klient kl3 = new Klient("Adrianna", "Iwan", "Ogrodowa 23 Poznan", "784983241");
        entityManager.persist(kl3);
        Usluga us4 = new Usluga(kl3, "naprawa 4", format.parse("2018-01-02"), format.parse("2018-01-11"));
        entityManager.persist(us4);
        Czynnosc cz8 = new Czynnosc(us4, 300, "Wymiana drazkow kierowniczych");
        entityManager.persist(cz8);
        Czynnosc cz9 = new Czynnosc(us4, 100, "Wymiana kompletu filtrow");
        entityManager.persist(cz9);

        entityManager.getTransaction().commit();
    }catch (Throwable ex) {
        System.err.println("utworz baze failed");
        ex.printStackTrace();
        entityManager.getTransaction().rollback();
    }
}
}
