import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOModel {
    public UserBeanModel findUserByEmailAndPassword(String email, String password) {
        ConnexionBDDModele connexionBDDModele = new ConnexionBDDModele();
        Connection connexion = connexionBDDModele.getConnexion();
        UserBeanModel user = null;

        try {
            PreparedStatement statement = connexion.prepareStatement("SELECT * FROM user WHERE mail=? AND mdp=?");
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new UserBeanModel();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setRole(resultSet.getString("role"));
                user.setSite(resultSet.getString("id_site"));
                user.setSoldeCP(resultSet.getFloat("solde_cp"));
                user.setDroitAnnuelCP(resultSet.getFloat("droit_annuel_cp"));
                user.setCompteurAbsence(resultSet.getInt("compteur_absences"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
