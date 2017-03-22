package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface UtilisateurDao {
	/*
	@SqlUpdate("create table utilisateurs(id integer primry key autoincrement,"
			+ "nom text, prenom text, login text, mdp text, email text,"
			+ "validation boolean, telephone text,entreprise text,numSiret text not null,role text)")
	void createUtilisateursTable();
	
    @SqlUpdate("insert into utilisateurs (nom,prenom,login,mdp,email,telephone,entreprise,numSiret,) values"
    		+ " (:nom, :prenom, :login, :mdp, :email , :telephone, :entreprise, :numSiret)")
    @GetGeneratedKeys
    int insert(@BindBean() Utilisateurs utilisateur);

    @SqlQuery("select * from utilisateurs where nom = :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateurs findByNom(@Bind("nom") String nom);

    @SqlQuery("select * from utilisateurs where search like :nom")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateurs> search(@Bind("libelle") String nom);

    @SqlUpdate("drop table if exists utilisateurs")
    void dropUtilisateursTable();

    @SqlUpdate("delete from utilisateurs where id = :id")
    void delete(@Bind("id") int id);

    @SqlQuery("select * from utilisateurs order by id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Utilisateurs> all();

    @SqlQuery("select * from utilisateurs where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Utilisateurs findById(@Bind("id") int id);

    void close();
*/
}
