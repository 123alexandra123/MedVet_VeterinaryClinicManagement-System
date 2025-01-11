package vet.system1;

public class Pacient {
    private int idAnimal;
    private String numeAnimal;
    private String gen;
    private int varsta;
    private String categorie;
    private String rasa;
    private String numeStapan;
    private String numarTelefonStapan;
    private String emailStapan;

    public Pacient(int idAnimal, String numeAnimal, String gen, int varsta, String categorie, String rasa, String numeStapan, String numarTelefonStapan, String emailStapan) {
        this.idAnimal = idAnimal;
        this.numeAnimal = numeAnimal;
        this.gen = gen;
        this.varsta = varsta;
        this.categorie = categorie;
        this.rasa = rasa;
        this.numeStapan = numeStapan;
        this.numarTelefonStapan = numarTelefonStapan;
        this.emailStapan = emailStapan;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getNumeAnimal() {
        return numeAnimal;
    }

    public void setNumeAnimal(String numeAnimal) {
        this.numeAnimal = numeAnimal;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getRasa() {
        return rasa;
    }

    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    public String getNumeStapan() {
        return numeStapan;
    }

    public void setNumeStapan(String numeStapan) {
        this.numeStapan = numeStapan;
    }

    public String getNumarTelefonStapan() {
        return numarTelefonStapan;
    }

    public void setNumarTelefonStapan(String numarTelefonStapan) {
        this.numarTelefonStapan = numarTelefonStapan;
    }

    public String getEmailStapan() {
        return emailStapan;
    }

    public void setEmailStapan(String emailStapan) {
        this.emailStapan = emailStapan;
    }

    @Override
    public String toString() {
        return "Pacient [Nume: " + numeAnimal + ", Rasa: " + rasa + ", Stăpân: " + numeStapan + "]";
    }
}

