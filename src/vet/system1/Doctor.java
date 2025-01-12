package vet.system1;

/**
 * Represents a doctor in the veterinary clinic system.
 * Contains details such as the doctor's ID, name, specialization, phone number, email, and password.
 */
public class Doctor {

    /**
     * The unique identifier of the doctor.
     */
    private int idDoctor;

    /**
     * The name of the doctor.
     */
    private String numeDoctor;

    /**
     * The specialization of the doctor.
     */
    private String specializare;

    /**
     * The phone number of the doctor.
     */
    private String numarTelefon;

    /**
     * The email address of the doctor.
     */
    private String email;

    /**
     * The password of the doctor (used for authentication).
     */
    private String parola;

    /**
     * Constructs a new Doctor with the specified details.
     *
     * @param idDoctor      The unique identifier of the doctor.
     * @param numeDoctor    The name of the doctor.
     * @param specializare  The specialization of the doctor.
     * @param numarTelefon  The phone number of the doctor.
     * @param email         The email address of the doctor.
     * @param parola        The password of the doctor.
     */
    public Doctor(int idDoctor, String numeDoctor, String specializare, String numarTelefon, String email, String parola) {
        this.idDoctor = idDoctor;
        this.numeDoctor = numeDoctor;
        this.specializare = specializare;
        this.numarTelefon = numarTelefon;
        this.email = email;
        this.parola = parola;
    }

    /**
     * Gets the unique identifier of the doctor.
     *
     * @return The doctor's ID.
     */
    public int getIdDoctor() {
        return idDoctor;
    }

    /**
     * Sets the unique identifier of the doctor.
     *
     * @param idDoctor The doctor's ID to set.
     */
    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    /**
     * Gets the name of the doctor.
     *
     * @return The doctor's name.
     */
    public String getNumeDoctor() {
        return numeDoctor;
    }

    /**
     * Sets the name of the doctor.
     *
     * @param numeDoctor The doctor's name to set.
     */
    public void setNumeDoctor(String numeDoctor) {
        this.numeDoctor = numeDoctor;
    }

    /**
     * Gets the specialization of the doctor.
     *
     * @return The doctor's specialization.
     */
    public String getSpecializare() {
        return specializare;
    }

    /**
     * Sets the specialization of the doctor.
     *
     * @param specializare The specialization to set.
     */
    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    /**
     * Gets the phone number of the doctor.
     *
     * @return The doctor's phone number.
     */
    public String getNumarTelefon() {
        return numarTelefon;
    }

    /**
     * Sets the phone number of the doctor.
     *
     * @param numarTelefon The phone number to set.
     */
    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    /**
     * Gets the email address of the doctor.
     *
     * @return The doctor's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the doctor.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the doctor.
     *
     * @return The doctor's password.
     */
    public String getParola() {
        return parola;
    }

    /**
     * Sets the password of the doctor.
     *
     * @param parola The password to set.
     */
    public void setParola(String parola) {
        this.parola = parola;
    }

    /**
     * Returns a string representation of the doctor.
     *
     * @return A string containing the doctor's name, specialization, and email.
     */
    @Override
    public String toString() {
        return "Doctor [Nume: " + numeDoctor + ", Specializare: " + specializare + ", Email: " + email + "]";
    }
}
