package com.examly.service;

import com.examly.entity.Contact;
import com.examly.util.DBConnectionUtil;

import java.sql.*;
import java.util.*;

public class ContactServiceImpl implements ContactService {

    public boolean addContact(Contact c) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "INSERT INTO contacts (contactname, phonenumber, status, lastseen, blocked) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getContactName());
            ps.setString(2, c.getPhoneNumber());
            ps.setString(3, c.getStatus());
            ps.setString(4, c.getLastSeen());
            ps.setBoolean(5, c.isBlocked());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public boolean updateContact(Contact c) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "UPDATE contacts SET contactname=?, phonenumber=?, status=?, lastseen=?, blocked=? WHERE contactid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getContactName());
            ps.setString(2, c.getPhoneNumber());
            ps.setString(3, c.getStatus());
            ps.setString(4, c.getLastSeen());
            ps.setBoolean(5, c.isBlocked());
            ps.setInt(6, c.getContactId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public boolean deleteContact(int id) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "DELETE FROM contacts WHERE contactid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public Contact getContactById(int id) {
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT * FROM contacts WHERE contactid=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Contact(
                        rs.getInt("contactid"),
                        rs.getString("contactname"),
                        rs.getString("phonenumber"),
                        rs.getString("status"),
                        rs.getString("lastseen"),
                        rs.getBoolean("blocked")
                );
            }
        } catch (Exception e) {}
        return null;
    }

    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT * FROM contacts";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Contact(
                        rs.getInt("contactid"),
                        rs.getString("contactname"),
                        rs.getString("phonenumber"),
                        rs.getString("status"),
                        rs.getString("lastseen"),
                        rs.getBoolean("blocked")
                ));
            }
        } catch (Exception e) {}
        return list;
    }

    public List<Contact> searchByName(String name) {
        List<Contact> list = new ArrayList<>();
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT * FROM contacts WHERE contactname LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Contact(
                        rs.getInt("contactid"),
                        rs.getString("contactname"),
                        rs.getString("phonenumber"),
                        rs.getString("status"),
                        rs.getString("lastseen"),
                        rs.getBoolean("blocked")
                ));
            }
        } catch (Exception e) {}
        return list;
    }

    public List<Contact> filterByBlocked(boolean blocked) {
        List<Contact> list = new ArrayList<>();
        try {
            Connection con = DBConnectionUtil.getConnection();
            String sql = "SELECT * FROM contacts WHERE blocked=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, blocked);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Contact(
                        rs.getInt("contactid"),
                        rs.getString("contactname"),
                        rs.getString("phonenumber"),
                        rs.getString("status"),
                        rs.getString("lastseen"),
                        rs.getBoolean("blocked")
                ));
            }
        } catch (Exception e) {}
        return list;
    }
}