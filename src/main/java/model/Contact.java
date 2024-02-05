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
    //constructeur vide
    public Contact() {
    }
    //getter & setter
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


    // CRUD implementation/ implementation FacadeContact
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

            stm.executeUpdate();
            System.out.println("Exécution réussie");
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
    public int[] getAllId_Contacts() {
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT ID_CONTACT FROM contact");
             ResultSet rs = stm.executeQuery()) {

            List<Integer> contactIds = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("ID_CONTACT");
                contactIds.add(id);
            }

            int[] result = new int[contactIds.size()];
            for (int i = 0; i < contactIds.size(); i++) {
                result[i] = contactIds.get(i);
            }

            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return new int[0]; // handle the exception as needed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Contact getById(int id) {
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("SELECT * FROM contact WHERE ID_CONTACT = ?");
        ) {
            stm.setInt(1, id);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    String firstname = rs.getString("FIRSTNAME");
                    String lastname = rs.getString("LASTNAME");
                    String email = rs.getString("EMAIL");
                    String phone = rs.getString("PHONE");
                    String address = rs.getString("ADDRESS");

                    return new Contact(id, firstname, lastname, email, phone, address);
                } else {
                    System.out.println("Contact not found with ID: " + id);
                    return null; // or throw an exception if not found
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null; // handle the exception as needed
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void deleteContact(int id) {
        try (Connection conn = ConnexionBD.getConnection();
             PreparedStatement stm = conn.prepareStatement("DELETE FROM contact WHERE ID_CONTACT = ?")) {
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
