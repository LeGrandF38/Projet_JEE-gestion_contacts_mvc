package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contact implements FacadeContact {
    private int ID_CONTACT;
    private String FIRSTNAME, LASTNAME, EMAIL, PHONE, ADDRESS;

    public Contact(int ID_CONTACT, String FIRSTNAME, String LASTNAME, String EMAIL, String PHONE, String ADDRESS) {
        this.ID_CONTACT = ID_CONTACT;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.ADDRESS = ADDRESS;
    }

    public Contact() {

    }

    @Override
    public void createContact(Contact c) {
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement(
                     "INSERT INTO contact (FIRSTNAME, LASTNAME, EMAIL, PHONE, ADDRESS) VALUES (?, ?, ?, ?, ?)")) {
            stm.setString(1, c.getFIRSTNAME());
            stm.setString(2, c.getLASTNAME());
            stm.setString(3, c.getEMAIL());
            stm.setString(4, c.getPHONE());
            stm.setString(5, c.getADDRESS());
            stm.setInt(6, c.getID_CONTACT());

            stm.executeUpdate();
            System.out.println("Execution ok");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Contact> searchContacts(String keyword) {
        List<Contact> searchResults = new ArrayList<>();

        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement(
                     "SELECT * FROM contacts WHERE FIRSTNAME LIKE ? OR LASTNAME LIKE ? OR ADDRESS LIKE ?")) {

            stm.setString(1, "%" + keyword + "%"); // Recherche par prénom
            stm.setString(2, "%" + keyword + "%"); // Recherche par nom
            stm.setString(3, "%" + keyword + "%"); // Recherche par adresse

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("ID_CONTACT");
                    String firstname = rs.getString("FIRSTNAME");
                    String lastname = rs.getString("LASTNAME");
                    String email = rs.getString("EMAIL");
                    String phone = rs.getString("PHONE");
                    String address = rs.getString("ADDRESS");

                    Contact contact = new Contact(id, firstname, lastname, email, phone, address);
                    searchResults.add(contact);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return searchResults;
    }


    public int getID_CONTACT() {
        return ID_CONTACT;
    }

    public void setID_CONTACT(int ID_CONTACT) {
        this.ID_CONTACT = ID_CONTACT;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    @Override
    public void deleteContact(int id) {
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("DELETE FROM contacts WHERE ID_CONTACT = ?")) {
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Execution ok");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public List<Contact> findAll() {
        List<Contact> contacts = new ArrayList<>();
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT * FROM contact");
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_CONTACT");
                String firstname = rs.getString("FIRSTNAME");
                String lastname = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                String phone = rs.getString("PHONE");
                String address = rs.getString("ADDRESS");

                Contact contact = new Contact(id, firstname, lastname, email, phone, address);
                contacts.add(contact);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    @Override
    public void updateContact(Contact newc) {
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement(
                     "UPDATE contact SET FIRSTNAME=?, LASTNAME=?, EMAIL=?, PHONE=?, ADDRESS=? WHERE ID_CONTACT=?")) {
            stm.setString(1, newc.getFIRSTNAME());
            stm.setString(2, newc.getLASTNAME());
            stm.setString(3, newc.getEMAIL());
            stm.setString(4, newc.getPHONE());
            stm.setString(5, newc.getADDRESS());
            stm.setInt(6, newc.getID_CONTACT());

            stm.executeUpdate();
            System.out.println("Contact mis à jour avec succès.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public int[] getAllIdContacts() {
        List<Integer> ids = new ArrayList<>();
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT ID_CONTACT FROM contact");
             ResultSet rs = stm.executeQuery()) {

            while (rs.next()) {
                ids.add(rs.getInt("ID_CONTACT"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Convertir la liste d'Integer en tableau d'int
        int[] result = new int[ids.size()];
        for (int i = 0; i < ids.size(); i++) {
            result[i] = ids.get(i);
        }

        return result;
    }


    @Override
    public Contact getById(int id) {
        Contact contact = null;
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT * FROM contact WHERE ID_CONTACT=?")) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String firstname = rs.getString("FIRSTNAME");
                    String lastname = rs.getString("LASTNAME");
                    String email = rs.getString("EMAIL");
                    String phone = rs.getString("PHONE");
                    String address = rs.getString("ADDRESS");

                    contact = new Contact(id, firstname, lastname, email, phone, address);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return contact;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "ID_CONTACT=" + ID_CONTACT +
                ", FIRSTNAME='" + FIRSTNAME + '\'' +
                ", LASTNAME='" + LASTNAME + '\'' +
                ", EMAIL='" + EMAIL + '\'' +
                ", PHONE='" + PHONE + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                '}';
    }
}
