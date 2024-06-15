/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.members;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import sample.passengers.PassengerDTO;
import sample.utils.DBUtils;

/**
 *
 * @author phamx
 */
public class MemberDAO {
    Logger logger = Logger.getLogger(getClass().getName());
    private static final String COLUMN_MEMBER_ID = "memberID";
    private static final String COLUMN_MEMBER_NUMBER = "memberNumber";
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_BIRTHDAY = "birthday";
    private static final String COLUMN_GENDER = "gender";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE_NO = "phoneno";
    private static final String COLUMN_NATIONAL = "nationality";
    private static final String COLUMN_NUMBER_ID = "numberID";

    public MemberDTO checkLogin(String userIdentity, String password) {
        MemberDTO member = null;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT memberID, memberNumber, firstname, lastname, birthday, gender, email, phoneno, nationality, numberID"
                    + " FROM Members"
                    + " WHERE (memberNumber = ? OR email = ?) AND password = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, userIdentity);
            stmt.setString(2, userIdentity);
            stmt.setString(3, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                member = new MemberDTO();
                member.setMemberID(rs.getInt(COLUMN_MEMBER_ID));
                member.setMemberNumber(rs.getString(COLUMN_MEMBER_NUMBER));
                member.setFirstname(rs.getString(COLUMN_FIRSTNAME));
                member.setLastname(rs.getString(COLUMN_LASTNAME));
                member.setBirthday(rs.getDate(COLUMN_BIRTHDAY).toLocalDate());
                member.setGender(rs.getString(COLUMN_GENDER));
                member.setEmail(rs.getString(COLUMN_EMAIL));
                member.setPhoneno(rs.getString(COLUMN_PHONE_NO));
                member.setNationality(rs.getString(COLUMN_NATIONAL));
                member.setNumberID(rs.getString(COLUMN_NUMBER_ID));
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in checkLogin. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return member;
    }

    public List<MemberDTO> list(String keyword) {
        List<MemberDTO> list = new ArrayList<>();
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT memberID, memberNumber, firstname, lastname, birthday, gender, email, phoneno, nationality, numberID"
                    + " FROM Members";
            if (keyword != null && !keyword.isEmpty()) {
                sql += " WHERE memberNumber like ? OR firstname like ? OR lastname like ? OR email like ?";
            }
            PreparedStatement stmt = con.prepareStatement(sql);
            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(1, "%" + keyword + "%");
                stmt.setString(2, "%" + keyword + "%");
                stmt.setString(3, "%" + keyword + "%");
                stmt.setString(4, "%" + keyword + "%");
            }
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MemberDTO member = new MemberDTO();
                member.setMemberID(rs.getInt(COLUMN_MEMBER_ID));
                member.setMemberNumber(rs.getString(COLUMN_MEMBER_NUMBER));
                member.setFirstname(rs.getString(COLUMN_FIRSTNAME));
                member.setLastname(rs.getString(COLUMN_LASTNAME));
                member.setBirthday(rs.getDate(COLUMN_BIRTHDAY).toLocalDate());
                member.setGender(rs.getString(COLUMN_GENDER));
                member.setEmail(rs.getString(COLUMN_EMAIL));
                member.setPhoneno(rs.getString(COLUMN_PHONE_NO));
                member.setNationality(rs.getString(COLUMN_NATIONAL));
                member.setNumberID(rs.getString(COLUMN_NUMBER_ID));
                list.add(member);
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in list members. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return list;
    }

    public MemberDTO load(int memberID) {
        MemberDTO member = null;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT memberNumber, firstname, lastname, birthday, gender, email, phoneno, nationality, numberID"
                    + " FROM Members"
                    + " WHERE memberID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, memberID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                member = new MemberDTO();
                member.setMemberID(memberID);
                member.setMemberNumber(rs.getString(COLUMN_MEMBER_NUMBER));
                member.setFirstname(rs.getString(COLUMN_FIRSTNAME));
                member.setLastname(rs.getString(COLUMN_LASTNAME));
                member.setBirthday(rs.getDate(COLUMN_BIRTHDAY).toLocalDate());
                member.setGender(rs.getString(COLUMN_GENDER));
                member.setEmail(rs.getString(COLUMN_EMAIL));
                member.setPhoneno(rs.getString(COLUMN_PHONE_NO));
                member.setNationality(rs.getString(COLUMN_NATIONAL));
                member.setNumberID(rs.getString(COLUMN_NUMBER_ID));
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in load member by id. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return member;
    }

    public MemberDTO load(String memberNumber) {
        MemberDTO member = null;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT memberID, firstname, lastname, birthday, gender, email, phoneno, nationality, numberID"
                    + " FROM Members"
                    + " WHERE memberNumber = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, memberNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                member = new MemberDTO();
                member.setMemberID(rs.getInt(COLUMN_MEMBER_ID));
                member.setMemberNumber(memberNumber);
                member.setFirstname(rs.getString(COLUMN_FIRSTNAME));
                member.setLastname(rs.getString(COLUMN_LASTNAME));
                member.setBirthday(rs.getDate(COLUMN_BIRTHDAY).toLocalDate());
                member.setGender(rs.getString(COLUMN_GENDER));
                member.setEmail(rs.getString(COLUMN_EMAIL));
                member.setPhoneno(rs.getString(COLUMN_PHONE_NO));
                member.setNationality(rs.getString(COLUMN_NATIONAL));
                member.setNumberID(rs.getString(COLUMN_NUMBER_ID));
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in load member by member number. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return member;
    }

    public boolean isEmailRegistered(String email) {
        boolean check = false;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT 1"
                    + " FROM Members"
                    + " WHERE email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                check = true;
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in check email exist. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return check;
    }

    public Integer insert(MemberDTO member) {
        Integer memberID = null;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "INSERT INTO Members(firstname, lastname, birthday, gender, email, phoneno, nationality, numberID, password)"
                    + " VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, member.getFirstname());
            stmt.setString(2, member.getLastname());
            stmt.setString(3, member.getBirthday().toString());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getEmail());
            stmt.setString(6, member.getPhoneno());
            stmt.setString(7, member.getNationality());
            stmt.setString(8, member.getNumberID());
            stmt.setString(9, member.getPassword());

            if (stmt.executeUpdate() == 1) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    memberID = rs.getInt(1);
                }
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in insert member. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return memberID;
    }

    public String getMemberNumber(String email) {
        String memberno = null;
        try {
            Connection con = DBUtils.getConnection();
            String sql = "SELECT memberNumber"
                    + " FROM Members"
                    + " WHERE email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                memberno = rs.getString(COLUMN_MEMBER_NUMBER);
            }
            con.close();
        } catch (SQLException ex) {
            logger.info("Error in get member number. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return memberno;
    }

    public boolean update(MemberDTO member) {
        boolean check = false;
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "UPDATE Members"
                    + " SET firstname = ?, lastname = ?, birthday = ?, gender = ?, email = ?, phoneno = ?, nationality = ?, numberID = ?"
                    + " WHERE memberID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, member.getFirstname());
            stmt.setString(2, member.getLastname());
            stmt.setString(3, member.getBirthday().toString());
            stmt.setString(4, member.getGender());
            stmt.setString(5, member.getEmail());
            stmt.setString(6, member.getPhoneno());
            stmt.setString(7, member.getNationality());
            stmt.setString(8, member.getNumberID());
            stmt.setInt(9, member.getMemberID());
            check = stmt.executeUpdate() > 0;
            conn.close();
        } catch (SQLException ex) {
            logger.info("Error in update member. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return check;
    }

    public boolean delete(int memberID) {
        boolean check = false;
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "DELETE Members"
                    + " WHERE memberID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, memberID);
            check = stmt.executeUpdate() > 0;
            conn.close();
        } catch (SQLException ex) {
            logger.info("Error in delete member. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return check;
    }

    public Integer checkMembership(PassengerDTO passenger, String memberNumber) {
        Integer memberID = null;
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "SELECT memberID"
                    + " FROM Members"
                    + " WHERE memberNumber = ? AND firstname = ? AND lastname = ? AND birthday = ? AND email = ? AND phoneno = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, memberNumber);
            stmt.setString(2, passenger.getFirstname());
            stmt.setString(3, passenger.getLastname());
            stmt.setString(4, passenger.getBirthday().toString());
            stmt.setString(5, passenger.getEmail());
            stmt.setString(6, passenger.getPhoneno());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                memberID = rs.getInt(COLUMN_MEMBER_ID);
            }
            conn.close();
        } catch (SQLException ex) {
            logger.info("Error in check membership. Details:" + ex.getMessage());
            ex.printStackTrace();
        }
        return memberID;
    }
}
