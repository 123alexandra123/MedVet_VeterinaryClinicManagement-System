
package vet.system1;

import java.time.LocalDate;

/**
 * Represents a medical record for an animal.
 * Contains details such as the record ID, doctor ID, animal ID, diagnostic, treatment recommendations, and appointment date.
 */

public class FisaMedicala {

    /** The unique identifier of the medical record. */
    private int idFisa;

    /** The unique identifier of the doctor associated with the record. */
    private int idDoctor;

    /** The unique identifier of the animal associated with the record. */
    private int idAnimal;

    /** The diagnostic information for the animal. */
    private String diagnostic;

    /** The recommended treatment or recommendations for the animal. */
    private String tratamentRecomandari;

    /** The scheduled appointment date for the animal. */
    private LocalDate dataProgramare;

    /**
     * Constructs a new medical record with the specified details.
     *
     * @param idFisa The unique identifier of the medical record.
     * @param idDoctor The unique identifier of the doctor.
     * @param idAnimal The unique identifier of the animal.
     * @param diagnostic The diagnostic information for the animal.
     * @param tratamentRecomandari The treatment recommendations.
     * @param dataProgramare The scheduled appointment date.
     */
    public FisaMedicala(int idFisa, int idDoctor, int idAnimal, String diagnostic, String tratamentRecomandari, LocalDate dataProgramare) {
        this.idFisa = idFisa;
        this.idDoctor = idDoctor;
        this.idAnimal = idAnimal;
        this.diagnostic = diagnostic;
        this.tratamentRecomandari = tratamentRecomandari;
        this.dataProgramare = dataProgramare;
    }

    /**
     * Gets the unique identifier of the medical record.
     *
     * @return The record ID.
     */
    public int getIdFisa() {
        return idFisa;
    }

    /**
     * Sets the unique identifier of the medical record.
     *
     * @param idFisa The record ID to set.
     */
    public void setIdFisa(int idFisa) {
        this.idFisa = idFisa;
    }

    /**
     * Gets the unique identifier of the doctor associated with the record.
     *
     * @return The doctor ID.
     */
    public int getIdDoctor() {
        return idDoctor;
    }

    /**
     * Sets the unique identifier of the doctor associated with the record.
     *
     * @param idDoctor The doctor ID to set.
     */
    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * Gets the unique identifier of the animal associated with the record.
     *
     * @return The animal ID.
     */
    public int getIdAnimal() {
        return idAnimal;
    }

    /**
     * Sets the unique identifier of the animal associated with the record.
     *
     * @param idAnimal The animal ID to set.
     */
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    /**
     * Gets the diagnostic information for the animal.
     *
     * @return The diagnostic information.
     */
    public String getDiagnostic() {
        return diagnostic;
    }

    /**
     * Sets the diagnostic information for the animal.
     *
     * @param diagnostic The diagnostic information to set.
     */
    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    /**
     * Gets the treatment recommendations for the animal.
     *
     * @return The treatment recommendations.
     */
    public String getTratamentRecomandari() {
        return tratamentRecomandari;
    }

    /**
     * Sets the treatment recommendations for the animal.
     *
     * @param tratamentRecomandari The treatment recommendations to set.
     */
    public void setTratamentRecomandari(String tratamentRecomandari) {
        this.tratamentRecomandari = tratamentRecomandari;
    }

    /**
     * Gets the scheduled appointment date for the animal.
     *
     * @return The appointment date.
     */
    public LocalDate getDataProgramare() {
        return dataProgramare;
    }

    /**
     * Sets the scheduled appointment date for the animal.
     *
     * @param dataProgramare The appointment date to set.
     */
    public void setDataProgramare(LocalDate dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    /**
     * Returns a string representation of the medical record.
     *
     * @return A string containing diagnostic, treatment, and appointment date details.
     */
    @Override
    public String toString() {
        return "Fișă Medicală [Diagnostic: " + diagnostic + ", Tratament: " + tratamentRecomandari + ", Data: " + dataProgramare + "]";
    }
}
