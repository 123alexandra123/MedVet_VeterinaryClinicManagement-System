
package vet.system1;

/**
 * Represents a patient in the veterinary system.
 * A patient is an animal with a lot of details.
 */

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

    /**
     * Constructs a Pacient with all the necessary details.
     *
     * @param idAnimal          The ID of the animal.
     * @param numeAnimal        The name of the animal.
     * @param gen               The gender of the animal.
     * @param varsta            The age of the animal.
     * @param categorie         The category of the animal (e.g., dog, cat).
     * @param rasa              The breed of the animal.
     * @param numeStapan        The name of the animal's owner.
     * @param numarTelefonStapan The phone number of the animal's owner.
     * @param emailStapan       The email address of the animal's owner.
     */
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

    /**
     * Gets the animal's ID.
     *
     * @return The ID of the animal.
     */
    public int getIdAnimal() {
        return idAnimal;
    }

    /**
     * Sets the animal's ID.
     *
     * @param idAnimal The new ID of the animal.
     */
    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    /**
     * Gets the animal's name.
     *
     * @return The name of the animal.
     */
    public String getNumeAnimal() {
        return numeAnimal;
    }

    /**
     * Sets the animal's name.
     *
     * @param numeAnimal The new name of the animal.
     */
    public void setNumeAnimal(String numeAnimal) {
        this.numeAnimal = numeAnimal;
    }

    /**
     * Gets the gender of the animal.
     *
     * @return The gender of the animal.
     */
    public String getGen() {
        return gen;
    }

    /**
     * Sets the gender of the animal.
     *
     * @param gen The new gender of the animal.
     */
    public void setGen(String gen) {
        this.gen = gen;
    }

    /**
     * Gets the age of the animal.
     *
     * @return The age of the animal.
     */
    public int getVarsta() {
        return varsta;
    }

    /**
     * Sets the age of the animal.
     *
     * @param varsta The new age of the animal.
     */
    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    /**
     * Gets the category of the animal.
     *
     * @return The category of the animal.
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Sets the category of the animal.
     *
     * @param categorie The new category of the animal.
     */
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    /**
     * Gets the breed of the animal.
     *
     * @return The breed of the animal.
     */
    public String getRasa() {
        return rasa;
    }

    /**
     * Sets the breed of the animal.
     *
     * @param rasa The new breed of the animal.
     */
    public void setRasa(String rasa) {
        this.rasa = rasa;
    }

    /**
     * Gets the owner's name.
     *
     * @return The name of the owner.
     */
    public String getNumeStapan() {
        return numeStapan;
    }

    /**
     * Sets the owner's name.
     *
     * @param numeStapan The new name of the owner.
     */
    public void setNumeStapan(String numeStapan) {
        this.numeStapan = numeStapan;
    }

    /**
     * Gets the owner's phone number.
     *
     * @return The phone number of the owner.
     */
    public String getNumarTelefonStapan() {
        return numarTelefonStapan;
    }

    /**
     * Sets the owner's phone number.
     *
     * @param numarTelefonStapan The new phone number of the owner.
     */
    public void setNumarTelefonStapan(String numarTelefonStapan) {
        this.numarTelefonStapan = numarTelefonStapan;
    }

    /**
     * Gets the owner's email address.
     *
     * @return The email address of the owner.
     */
    public String getEmailStapan() {
        return emailStapan;
    }

    /**
     * Sets the owner's email address.
     *
     * @param emailStapan The new email address of the owner.
     */
    public void setEmailStapan(String emailStapan) {
        this.emailStapan = emailStapan;
    }

    /**
     * Returns a string representation of the patient.
     *
     * @return A string describing the patient.
     */
    @Override
    public String toString() {
        return "Pacient [Nume: " + numeAnimal + ", Rasa: " + rasa + ", Stăpân: " + numeStapan + "]";
    }
}
