    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quanlykhachsan.dao;

import com.quanlykhachsan.entity.DichVu;
import com.quanlykhachsan.entity.Voucher;
import com.quanlykhachsan.model.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DichVu_DAO {
    ArrayList<DichVu> dsDichVu ;
    public DichVu_DAO(){
        
    }
    public ArrayList<DichVu> getDsDichVu() {
       dsDichVu = new ArrayList<DichVu>();
       try {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement("select * from DichVu");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String maDichVu = rs.getString(1);
            String tenDichVu = rs.getString(2);
            String moTa = rs.getString(3);
            int gia = rs.getInt(4);
            DichVu dv = new DichVu(maDichVu, tenDichVu,moTa, gia);
            dsDichVu.add(dv);
        }
       } catch (SQLException e) {
        e.printStackTrace();
       }
         return dsDichVu;
    }
     public void doctubang() {
       dsDichVu = new ArrayList<DichVu>();
       try {
        Connection con = ConnectDB.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement("select * from DichVu");
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            String maDichVu = rs.getString(1);
            String tenDichVu = rs.getString(2);
            String moTa = rs.getString(3);
            int gia = rs.getInt(4);
            DichVu dv = new DichVu(maDichVu, tenDichVu,moTa, gia);
            dsDichVu.add(dv);
        }
       } catch (SQLException e) {
        e.printStackTrace();
       }
         
    }
   public boolean themDichVu1(DichVu voucher){
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO DichVu VALUES(?,?,?, ?)");
            ps.setString(1, voucher.getMaDichVu());
            ps.setString(2, voucher.getTenDichVu());
            ps.setString(3, voucher.getMoTa());
            ps.setDouble(4, voucher.getGiaDichVu());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
   }
    public boolean capNhatDichVu(DichVu dichVuCapNhat, String maDichVuCanCapNhap){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection()
            .prepareStatement("update DichVu set tenDichVu = ?, moTa = ?, giaDichVu = ? where maDichVu = ?");
            ps.setString(1, dichVuCapNhat.getTenDichVu());
            ps.setString(2, dichVuCapNhat.getMoTa());
            ps.setDouble(3, dichVuCapNhat.getGiaDichVu());
            ps.setString(4, maDichVuCanCapNhap);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public DichVu timDichVu (String maDichVuCanTim){
        try {
            PreparedStatement ps = ConnectDB.getInstance().getConnection()
            .prepareStatement("select * from DichVu where MaDichVu = ?");
            ps.setString(1, maDichVuCanTim);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String maDichVu = rs.getString(1);
                String tenDichVu = rs.getString(2);
                String moTa = rs.getString(3);
                double gia = rs.getDouble(4);
                return new DichVu(maDichVu, tenDichVu, moTa, gia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return null;
}
}
