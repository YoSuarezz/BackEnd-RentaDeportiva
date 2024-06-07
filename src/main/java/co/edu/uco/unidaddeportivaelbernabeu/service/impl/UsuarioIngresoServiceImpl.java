package co.edu.uco.unidaddeportivaelbernabeu.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import co.edu.uco.unidaddeportivaelbernabeu.dto.UsuarioIngresoDTO;
import co.edu.uco.unidaddeportivaelbernabeu.service.UsuarioIngresoService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioIngresoServiceImpl implements UsuarioIngresoService {

    @Override
    public boolean validarUsuario(UsuarioIngresoDTO usuarioIngreso) {
        String url = "jdbc:sqlserver://unidaddeportivaelbernabeu-server.database.windows.net:1433;database=UnidadDeportivaElBernabeu;user=Administrador@unidaddeportivaelbernabeu-server;password=ProyectoDoo*;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM UsuarioIngreso WHERE usuario = ? AND contraseña = ? AND esta_activo = 1")) {

            stmt.setString(1, usuarioIngreso.getUsuario());
            stmt.setString(2, usuarioIngreso.getContrasena());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
