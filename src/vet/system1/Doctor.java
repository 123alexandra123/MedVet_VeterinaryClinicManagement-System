package vet.system1;

public class Doctor {
    private int idDoctor;
    private String numeDoctor;
    private String specializare;
    private String numarTelefon;
    private String email;
    private String parola;

    public Doctor(int idDoctor, String numeDoctor, String specializare, String numarTelefon, String email, String parola) {
        this.idDoctor = idDoctor;
        this.numeDoctor = numeDoctor;
        this.specializare = specializare;
        this.numarTelefon = numarTelefon;
        this.email = email;
        this.parola = parola;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getNumeDoctor() {
        return numeDoctor;
    }

    public void setNumeDoctor(String numeDoctor) {
        this.numeDoctor = numeDoctor;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    @Override
    public String toString() {
        return "Doctor [Nume: " + numeDoctor + ", Specializare: " + specializare + ", Email: " + email + "]";
    }
}
