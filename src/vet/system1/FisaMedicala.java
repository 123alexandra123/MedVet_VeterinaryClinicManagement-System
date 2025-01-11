package vet.system1;

import java.time.LocalDate;

public class FisaMedicala {
    private int idFisa;
    private int idDoctor;
    private int idAnimal;
    private String diagnostic;
    private String tratamentRecomandari;
    private LocalDate dataProgramare;

    public FisaMedicala(int idFisa, int idDoctor, int idAnimal, String diagnostic, String tratamentRecomandari, LocalDate dataProgramare) {
        this.idFisa = idFisa;
        this.idDoctor = idDoctor;
        this.idAnimal = idAnimal;
        this.diagnostic = diagnostic;
        this.tratamentRecomandari = tratamentRecomandari;
        this.dataProgramare = dataProgramare;
    }

    public int getIdFisa() {
        return idFisa;
    }

    public void setIdFisa(int idFisa) {
        this.idFisa = idFisa;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTratamentRecomandari() {
        return tratamentRecomandari;
    }

    public void setTratamentRecomandari(String tratamentRecomandari) {
        this.tratamentRecomandari = tratamentRecomandari;
    }

    public LocalDate getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(LocalDate dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    @Override
    public String toString() {
        return "Fișă Medicală [Diagnostic: " + diagnostic + ", Tratament: " + tratamentRecomandari + ", Data: " + dataProgramare + "]";
    }
}
