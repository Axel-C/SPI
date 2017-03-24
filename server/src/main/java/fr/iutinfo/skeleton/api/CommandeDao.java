package fr.iutinfo.skeleton.api;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import java.util.List;

public interface CommandeDao {
	@SqlUpdate("create table commandes(idc integer primary key autoincrement,"
			+ "idp integer,id integer,"
			+ "prixTotal float,"
			+ "Constraint fk_produits foreign key(idp) references produits(idp),"
			+ "Constraint fk_users foreign key(id) references users(id))")
	void createCommandeTable();
	
    @SqlUpdate("insert into commandes(id, idp,prixTotal) values"
    		+ " (:id, :idp, :prixTotal)")
    @GetGeneratedKeys
    int insert(@BindBean() Commandes commande);

    @SqlQuery("select * from commandes where idc = :idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Commandes findByIdc(@Bind("idc") Integer idc);

    @SqlQuery("select * from commandes where search like :idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Commandes> search(@Bind("idc") Integer idc);

    @SqlUpdate("drop table if exists commandes")
    void dropCommandeTable();

    @SqlUpdate("delete from commandes where idc = :idc")
    void delete(@Bind("idc") int idc);

    @SqlQuery("select * from commandes order by idc")
    @RegisterMapperFactory(BeanMapperFactory.class)
    List<Commandes> all();

    @SqlQuery("select * from commandes where id = :id")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Commandes findById(@Bind("id") int id);
    
    @SqlQuery("select * from commandes where idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Commandes findByIdp(@Bind("idp") int idp);
    
    @SqlQuery("update commandes set id =:id , idp =:idp, prixTotal = :prixTotal where idp = :idp")
    @RegisterMapperFactory(BeanMapperFactory.class)
    Commandes updateCommandes(@Bind("idc") int idc);

    void close();
}
