
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ProdDAOimpl extends ConexionBD implements ProdDAO{

    @Override
    public void isert(Producto producto) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            this.conectar();
            String sql = "INSERT into productos (descripcion,stock) values (?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2, producto.getStock());

            //ejecuta la consilta
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE productos set descripcion=?,stock=?  where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2, producto.getStock());
            ps.setInt(3, producto.getId());
            //ejecuta la consilta

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            String sql = "DELETE from productos where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            //ejecuta la consilta

            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Producto getById(int id) throws Exception {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         Producto pro = new Producto();

        try {
            this.conectar();
            String sql = "SELECT * from productos where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            //ejecuta la consilta

            ResultSet rs = ps.executeQuery();
            //verifica si tiene registro
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("stock"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pro;
    }

    @Override
    public List<Producto> getAll() throws Exception {
        List<Producto> lista = null;
        try {
            this.conectar();
            String sql = "SELECT * from productos";
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            //verifica si tiene registro
            lista = new ArrayList<Producto>();

            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("stock"));
                //adicionar a la coneccion
                
                lista.add(pro);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }
    
}
